package com.fedex.feedbackfrog.service;

import com.fedex.feedbackfrog.model.dto.GoogleAuthUserInfoDTO;
import com.fedex.feedbackfrog.model.entity.User;
import com.fedex.feedbackfrog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOidcUserService extends OidcUserService {

  private UserRepository userRepository;

  @Autowired
  public CustomOidcUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
    OidcUser oidcUser = super.loadUser(userRequest);
    Map attributes = oidcUser.getAttributes();
    GoogleAuthUserInfoDTO userInfoDTO = new GoogleAuthUserInfoDTO();
    userInfoDTO.setEmail((String) attributes.get("email"));
    userInfoDTO.setId((String) attributes.get("sub")); // miért sub?
    userInfoDTO.setImageUrl((String) attributes.get("picture"));
    userInfoDTO.setName((String) attributes.get("name"));
    updateUser(userInfoDTO);
    return oidcUser;
  }

  protected void updateUser(GoogleAuthUserInfoDTO userInfoDTO) {
    User user = userRepository.findByEmailAddress(userInfoDTO.getEmail());
    if (!userRepository.existsByEmailAddress(userInfoDTO.getEmail())) {
      user = new User();
    }
    user.setEmailAddress(userInfoDTO.getEmail());
  }
}
