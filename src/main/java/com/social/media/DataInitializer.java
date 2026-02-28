package com.social.media;

import com.social.media.models.Post;
import com.social.media.models.SocialGroup;
import com.social.media.models.SocialProfile;
import com.social.media.models.SocialUser;
import com.social.media.repositories.PostRepository;
import com.social.media.repositories.SocialGroupRepository;
import com.social.media.repositories.SocialProfileRepository;
import com.social.media.repositories.SocialUserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@AllArgsConstructor
public class DataInitializer {
    private final SocialUserRepository userRepository;
    private final SocialGroupRepository groupRepository;
    private final PostRepository postRepository;
    private final SocialProfileRepository socialProfileRepository;

    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            SocialUser user1 = new SocialUser();
            SocialUser user2 = new SocialUser();
            SocialUser user3 = new SocialUser();

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            SocialGroup group1 = new SocialGroup();
            SocialGroup group2 = new SocialGroup();

            group1.getSocialUserSet().add(user1);
            group1.getSocialUserSet().add(user2);

            group2.getSocialUserSet().add(user2);
            group2.getSocialUserSet().add(user3);

            groupRepository.save(group1);
            groupRepository.save(group2);

            user1.getGroups().add(group1);
            user2.getGroups().add(group1);
            user2.getGroups().add(group2);
            user3.getGroups().add(group2);

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            Post post1 = new Post();
            Post post2 = new Post();
            Post post3 = new Post();

            post1.setSocialUser(user1);
            post2.setSocialUser(user2);
            post3.setSocialUser(user3);

            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);

            SocialProfile profile1 = new SocialProfile();
            SocialProfile profile2 = new SocialProfile();
            SocialProfile profile3 = new SocialProfile();

            profile1.setUser(user1);
            profile2.setUser(user2);
            profile3.setUser(user3);

            socialProfileRepository.save(profile1);
            socialProfileRepository.save(profile2);
            socialProfileRepository.save(profile3);

            System.out.println("FETCHING SOCIAL USER");
            userRepository.findById(1L);
        };
    }
}