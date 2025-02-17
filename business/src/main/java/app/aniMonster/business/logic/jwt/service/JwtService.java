package app.aniMonster.business.logic.jwt.service;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.social.model.SocialSignResponse;
import app.aniMonster.business.logic.jwt.helper.TokenHelperIfs;
import app.aniMonster.business.logic.jwt.model.TokenModel;
import app.aniMonster.postgresql.db.social.enums.SocialIsAdult;
import app.aniMonster.postgresql.db.social.enums.SocialStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class JwtService {

    private final TokenHelperIfs tokenHelperIfs;

    public TokenModel issueAccessToken(Map<String, Object> data) {
        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenModel issueRefreshToken(Map<String, Object> data) {
        return tokenHelperIfs.issueRefreshToken(data);
    }

    public TokenModel issueAdminToken(Map<String, Object> data) { return tokenHelperIfs.issueAdminToken(data); }

    // 토큰 검증 및 사용자 정보 추출
    public Map<String, String> validateToken(String token) {
        //토큰 검증 및 클레임 추출
        var map = tokenHelperIfs.validationTokenWithThrow(token);

        var id = map.get("social_id");
        var adminId = map.get("admin_id");

        // 관리자 토큰인 경우 처리
        if (adminId != null) {
            id = "ADMIN_ADMINISTRATOR";
        } else {
            adminId = "ADMIN_USER";
        }

        // id 에외 처리
        Objects.requireNonNull(id, ()->{
            throw new BusinessException(BusinessErrorCode.NULL_POINT,"Invalid token id");
        });

        var idInfo = new HashMap<String, String>();
        idInfo.put("id", id.toString());
        idInfo.put("adminId", adminId.toString());
        System.out.println(idInfo.get("id"));
        return idInfo;
    }

    // 리프레시 토큰 검증 및 사용자 정보 추출
    public SocialSignResponse validateRefreshToken(String token) {
        // 토큰 검증 및 클레임 추출
        var map = tokenHelperIfs.validationTokenWithThrow(token);

        var id = map.get("social_id").toString();

        var isAdult_str = map.get("is_adult").toString();
        SocialIsAdult isAdult;
        if (isAdult_str.equals("REGISTERED")){
            isAdult = SocialIsAdult.REGISTERED;
        }else if(isAdult_str.equals("UNREGISTERED")){
            isAdult = SocialIsAdult.UNREGISTERED;
        }else{
            throw new BusinessException(BusinessErrorCode.NULL_POINT,"Invalid token");
        }

        var status_str = map.get("status").toString();
        SocialStatus status;
        if (status_str.equals("REGISTERED")){
            status = SocialStatus.REGISTERED;
        }else{
            throw new BusinessException(BusinessErrorCode.NULL_POINT,"Invalid token");
        }

        return SocialSignResponse.builder()
                .social_id(id)
                .status(status)
                .is_adult(isAdult)
                .build();
    }
}
