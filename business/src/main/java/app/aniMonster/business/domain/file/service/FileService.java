package app.aniMonster.business.domain.file.service;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.postgresql.db.file.entity.FileEntity;
import app.aniMonster.postgresql.db.file.enums.FileIsActivate;
import app.aniMonster.postgresql.db.file.repository.FileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileRepository fileRepository;

    @Transactional
    public FileEntity upload(FileEntity entity) {
        return fileRepository.save(entity);
    }

    @Transactional
    public FileEntity find(FileEntity entity) {
        return fileRepository.findFirstByFileIdAndIsActivateOrderByIdDesc(entity.getFileId(), FileIsActivate.ACTIVATED)
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "File not found"));
    }

    @Transactional
    public FileEntity deactivate(FileEntity entity) {
        var checkEntity = fileRepository.findFirstByFileIdAndIsActivateOrderByIdDesc(entity.getFileId(), FileIsActivate.ACTIVATED);

        return checkEntity
                .map(it -> {
                    it.setIsActivate(FileIsActivate.DEACTIVATED);
                    return it;
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "Not Found FileId"));

    }
}
