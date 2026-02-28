package com.social.media.services;

import com.social.media.models.SocialUser;
import com.social.media.repositories.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialService {

    @Autowired
    private SocialUserRepository socialUserRepository;

    public List<SocialUser> getAllUsers() {
        return socialUserRepository.findAll();
    }

    public SocialUser saveUser(SocialUser socialUser) {
        if (socialUser.getSocialProfile() != null) {
            socialUser.getSocialProfile().setUser(socialUser);
        }

        if (socialUser.getPosts() != null) {
            socialUser.getPosts().forEach(post -> post.setSocialUser(socialUser));
        }

        if (socialUser.getGroups() != null) {
            socialUser.getGroups().forEach(group -> group.getSocialUserSet().add(socialUser));
        }

        return socialUserRepository.save(socialUser);
    }
}
