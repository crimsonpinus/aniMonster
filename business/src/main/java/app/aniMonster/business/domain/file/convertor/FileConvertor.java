package app.aniMonster.business.domain.file.convertor;

import app.aniMonster.business.common.annotation.Converter;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.file.model.FileFindRequest;
import app.aniMonster.business.domain.file.model.FileRequest;
import app.aniMonster.business.domain.file.model.FileResponse;
import app.aniMonster.business.domain.social.business.SocialBusiness;
import app.aniMonster.postgresql.db.file.entity.FileEntity;
import app.aniMonster.postgresql.db.file.enums.FileIsActivate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.*;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Converter
public class FileConvertor {

    private final SocialBusiness socialBusiness;

    public FileEntity toEntity(FileRequest fileRequest, MultipartFile multipartFile) {
        return Optional.ofNullable(fileRequest)
                .map(request -> {
                    return Optional.ofNullable(multipartFile)
                            .map(file -> {
                                var now = Instant.now();
                                var ext = getExtension(file);
                                var filePath = getFilePath(request, now);
                                return FileEntity.builder()
                                        .fileName(file.getOriginalFilename())
                                        .fileExt(ext)
                                        .registeredAt(now)
                                        .category(request.getCategory())
                                        .fileId(now.getEpochSecond() + "_" + UUID.randomUUID().toString())
                                        .filePath(filePath)
                                        .isActivate(FileIsActivate.ACTIVATED)
                                        .build();
                            })
                            .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "File is Empty"));
                })
                .orElseThrow(() ->new BusinessException(BusinessErrorCode.NULL_POINT, "FileRequest is Null"));
    }

    public FileEntity toEntity(FileFindRequest request) {
        return Optional.ofNullable(request)
                .map(it -> {
                    return FileEntity.builder()
                            .fileId(request.getFile_id())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "FileDeactivateRequest is Null"));
    }


    public FileResponse toResponsse(FileEntity fileEntity) {
        return Optional.ofNullable(fileEntity)
                .map(it -> {
                    return FileResponse.builder()
                            .url(it.getFilePath() + it.getFileId() + "." + it.getFileExt())
                            .file_name(it.getFileName())
                            .file_ext(it.getFileExt())
                            .file_path(it.getFilePath())
                            .file_id(it.getFileId())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "FileResponsse is Null"));
    }








    public String getExtension(MultipartFile file) {
        var fileName = file.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private String getFilePath(FileRequest request, Instant now) {

        String path = "";
        var date = LocalDateTime.ofInstant(now, ZoneId.systemDefault());

        String finalPath = path;
        switch (request.getCategory()){
            // path/social/계정생성일/계정id 아래 저장
            case SOCIAL -> {
                var sRegisteredAt = socialBusiness.getRegisteredAt(request.getUpload_id());
                var sdate = LocalDateTime.ofInstant(sRegisteredAt, ZoneId.systemDefault());
                path = path + "/" +
                        request.getCategory().toString().toLowerCase() + "/" +
                        sdate.getYear() + "/" + sdate.getMonthValue() + "/" + sdate.getDayOfMonth() + "/" +
                        request.getUpload_id() + "/" ;
            }
            // path/contact/현재날짜/계정id 아래에 저장
            case CONTACT, BACKGROUND -> {
                path = path + "/" +
                        request.getCategory().toString().toLowerCase() + "/" +
                        date.getYear() + "/" + date.getMonthValue() + "/" + date.getDayOfMonth() + "/" +
                        request.getUpload_id() + "/" ;
            }
            // path/캐릭텀명(info에 담아서 옴)/현재날짜/계정id 아래에 저장
            case CHARACTER -> {
                path = path + "/" +
                        Optional.ofNullable(request.getInfo())
                        .map(it -> {
                            return  it + "/" +
                                    date.getYear() + "/" + date.getMonthValue() + "/" + date.getDayOfMonth() + "/" +
                                    request.getUpload_id() + "/";
                        })
                        .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Info is Null"));
            }
        }

        return path;
    }
}
