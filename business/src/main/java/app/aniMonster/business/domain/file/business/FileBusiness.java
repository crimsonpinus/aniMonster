package app.aniMonster.business.domain.file.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.file.convertor.FileConvertor;
import app.aniMonster.business.domain.file.model.FileFindRequest;
import app.aniMonster.business.domain.file.model.FileModifyRequest;
import app.aniMonster.business.domain.file.model.FileRequest;
import app.aniMonster.business.domain.file.model.FileResponse;
import app.aniMonster.business.domain.file.service.FileService;
import app.aniMonster.postgresql.db.file.entity.FileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Business
public class FileBusiness {

    private final FileConvertor fileConvertor;
    private final FileService fileService;


    @Value("${file.path}")
    private String PATH;

    //단일 업로드
    public FileResponse upload(FileRequest request, MultipartFile file) {
        var entity = fileConvertor.toEntity(request, file);
        saveFile(entity, file);
        System.out.println("File uploaded" + entity.toString());
        var usedEntity = fileService.upload(entity);

        return fileConvertor.toResponsse(usedEntity);
    }

    //다중 업로드
    public List<FileResponse> upload(FileRequest request, List<MultipartFile> files) {
        List<FileResponse> response = new ArrayList<>();
        files.forEach(file -> {
            if (!file.isEmpty()) {
                var res = upload(request, file);
                response.add(res);
            }
        });

        return response;
    }


    public FileResponse find(FileFindRequest request) {
        var entity = fileConvertor.toEntity(request);
        var usedEntity = fileService.find(entity);

        return fileConvertor.toResponsse(usedEntity);

    }

    public FileResponse deactivate(FileFindRequest request) {
        var entity = fileConvertor.toEntity(request);
        var usedEntity = fileService.deactivate(entity);

        return fileConvertor.toResponsse(usedEntity);

    }

    public FileResponse activationSwitch(FileModifyRequest request) {
        var entity = fileConvertor.toEntity(request);
        var usedEntity = fileService.activationSwitch(entity);

        return fileConvertor.toResponsse(usedEntity);

    }

    //파일 저장
    private void saveFile(FileEntity entity, MultipartFile file) {
        //폴더 확인 및 생성
        File folder = new File(PATH + entity.getFilePath());
        String fullPath = PATH + entity.getFilePath() + entity.getFileId() + "." + fileConvertor.getExtension(file);
        try {
            System.out.println("Saving file " + fullPath);
            if(!folder.exists()) {
                folder.mkdirs();
            }
            file.transferTo(new File(fullPath));
        } catch (IOException e) {
            throw new BusinessException(BusinessErrorCode.SERVER_ERROR,"File Save Error",e);
        }
    }

}
