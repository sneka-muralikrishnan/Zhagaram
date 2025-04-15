package com.tamilculture.config;

import com.tamilculture.model.*;
import com.tamilculture.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CulturalItemRepository culturalItemRepository;
    private final FoodRepository foodRepository;
    private final QuizQuestionRepository quizQuestionRepository;
    private final ForumPostRepository forumPostRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Initialize roles if they don't exist
        if (roleRepository.count() == 0) {
            Role userRole = new Role();
            userRole.setName(Role.ERole.ROLE_USER);
            
            Role modRole = new Role();
            modRole.setName(Role.ERole.ROLE_MODERATOR);
            
            Role adminRole = new Role();
            adminRole.setName(Role.ERole.ROLE_ADMIN);
            
            roleRepository.saveAll(List.of(userRole, modRole, adminRole));
        }
        
        // Create admin user if it doesn't exist
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@tamilculture.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFullName("Admin User");
            
            Set<Role> roles = new HashSet<>();
            roleRepository.findByName(Role.ERole.ROLE_ADMIN).ifPresent(roles::add);
            admin.setRoles(roles);
            
            userRepository.save(admin);
        }
        
        // Add sample cultural items if none exist
        if (culturalItemRepository.count() == 0) {
            User admin = userRepository.findByUsername("admin").orElseThrow();
            
            CulturalItem item1 = new CulturalItem();
            item1.setTitle("Bharatanatyam Dance");
            item1.setDescription("Bharatanatyam is one of the oldest classical dance forms of India. It originated in Tamil Nadu and is known for its grace, purity, tenderness, and sculpturesque poses.");
            item1.setImageUrl("/images/bharatanatyam.jpg");
            item1.setCategory(CulturalItem.Category.DANCE);
            item1.setCreatedBy(admin);
            
            CulturalItem item2 = new CulturalItem();
            item2.setTitle("Kolam Art");
            item2.setDescription("Kolam is a form of drawing that is drawn by using rice flour, chalk, chalk powder or rock powder, often using colored powders in Tamil Nadu. A Kolam is a geometrical line drawing composed of curved loops, drawn around a grid pattern of dots.");
            item2.setImageUrl("/images/kolam.jpg");
            item2.setCategory(CulturalItem.Category.ART);
            item2.setCreatedBy(admin);
            
            CulturalItem item3 = new CulturalItem();
            item3.setTitle("Pongal Festival");
            item3.setDescription("Pongal is a four-day harvest festival celebrated in Tamil Nadu. It is a celebration to thank the Sun God for agricultural abundance. The word 'Pongal' in Tamil means 'to boil', and this festival is celebrated as a thanksgiving ceremony for the year's harvest.");
            item3.setImageUrl("/images/pongal.jpg");
            item3.setCategory(CulturalItem.Category.FESTIVAL);
            item3.setCreatedBy(admin);
            
            CulturalItem item4 = new CulturalItem();
            item4.setTitle("Carnatic Music");
            item4.setDescription("Carnatic music is a system of music commonly associated with South India, including the modern Indian states of Tamil Nadu, Karnataka, Andhra Pradesh, and Kerala. It is one of two main subgenres of Indian classical music that evolved from ancient Hindu traditions.");
            item4.setImageUrl("/images/carnatic.jpg");
            item4.setCategory(CulturalItem.Category.MUSIC);
            item4.setCreatedBy(admin);
            
            culturalItemRepository.saveAll(List.of(item1, item2, item3, item4));
        }
        
        // Add sample food items if none exist
        if (foodRepository.count() == 0) {
            User admin = userRepository.findByUsername("admin").orElseThrow();
            
            Food food1 = new Food();
            food1.setName("Idli");
            food1.setDescription("Idli is a traditional breakfast in Tamil households. It is a savory rice cake that is made by steaming a batter consisting of fermented black lentils and rice.");
            food1.setRecipe("1. Soak rice and urad dal separately for 4-5 hours.\n2. Grind them separately and mix together.\n3. Add salt and let it ferment overnight.\n4. Pour into idli molds and steam for 10-12 minutes.");
            food1.setImageUrl("/images/idli.jpg");
            food1.setCategory(Food.Category.BREAKFAST);
            food1.setCreatedBy(admin);
            
            Food food2 = new Food();
            food2.setName("Dosa");
            food2.setDescription("Dosa is a thin pancake or crepe originating from South India, made from a fermented batter predominantly consisting of lentils and rice.");
            food2.setRecipe("1. Soak rice and urad dal separately for 4-5 hours.\n2. Grind them separately and mix together.\n3. Add salt and let it ferment overnight.\n4. Heat a tawa, pour a ladle of batter and spread in circular motion.\n5. Cook until golden brown and crisp.");
            food2.setImageUrl("/images/dosa.jpg");
            food2.setCategory(Food.Category.BREAKFAST);
            food2.setCreatedBy(admin);
            
            Food food3 = new Food();
            food3.setName("Pongal");
            food3.setDescription("Pongal is a popular South Indian rice dish. In Tamil Nadu, it is a common dish for breakfast and lunch. It is most often served during the festival of the same name, Pongal.");
            food3.setRecipe("1. Dry roast rice and moong dal.\n2. Pressure cook with water, salt, and turmeric.\n3. In a pan, heat ghee and add cumin, pepper, cashews, and curry leaves.\n4. Pour the tempering over the cooked rice and dal mixture.");
            food3.setImageUrl("/images/pongal-dish.jpg");
            food3.setCategory(Food.Category.BREAKFAST);
            food3.setCreatedBy(admin);
            
            Food food4 = new Food();
            food4.setName("Sambar");
            food4.setDescription("Sambar is a lentil-based vegetable stew or chowder cooked with a tamarind broth. It is popular in South Indian cuisines.");
            food4.setRecipe("1. Cook toor dal until soft.\n2. In a pan, cook vegetables with tamarind extract, sambar powder, and salt.\n3. Add cooked dal and simmer.\n4. Prepare tempering with mustard seeds, curry leaves, and asafoetida.\n5. Add tempering to sambar and serve hot.");
            food4.setImageUrl("/images/sambar.jpg");
            food4.setCategory(Food.Category.LUNCH);
            food4.setCreatedBy(admin);
            
            foodRepository.saveAll(List.of(food1, food2, food3, food4));
        }
        
        // Add sample quiz questions if none exist
        if (quizQuestionRepository.count() == 0) {
            QuizQuestion q1 = new QuizQuestion();
            q1.setQuestionText("Which classical dance form originated in Tamil Nadu?");
            q1.setOptions(Arrays.asList("Kathak", "Bharatanatyam", "Kuchipudi", "Odissi"));
            q1.setCorrectOptionIndex(1);
            q1.setExplanation("Bharatanatyam is one of the oldest classical dance forms of India and originated in Tamil Nadu.");
            q1.setDifficulty(QuizQuestion.Difficulty.EASY);
            q1.setCategory(QuizQuestion.Category.DANCE);
            
            QuizQuestion q2 = new QuizQuestion();
            q2.setQuestionText("Which of these is a traditional Tamil harvest festival?");
            q2.setOptions(Arrays.asList("Onam", "Bihu", "Pongal", "Baisakhi"));
            q2.setCorrectOptionIndex(2);
            q2.setExplanation("Pongal is a four-day harvest festival celebrated in Tamil Nadu.");
            q2.setDifficulty(QuizQuestion.Difficulty.EASY);
            q2.setCategory(QuizQuestion.Category.FESTIVALS);
            
            QuizQuestion q3 = new QuizQuestion();
            q3.setQuestionText("Which ancient Tamil text is considered one of the oldest works of Tamil literature?");
            q3.setOptions(Arrays.asList("Tirukkural", "Silappatikaram", "Tolkappiyam", "Manimekalai"));
            q3.setCorrectOptionIndex(2);
            q3.setExplanation("Tolkappiyam is the oldest Tamil grammar text and is considered one of the earliest works of Tamil literature.");
            q3.setDifficulty(QuizQuestion.Difficulty.MEDIUM);
            q3.setCategory(QuizQuestion.Category.LITERATURE);
            
            QuizQuestion q4 = new QuizQuestion();
            q4.setQuestionText("Which of these is NOT a traditional Tamil musical instrument?");
            q4.setOptions(Arrays.asList("Veena", "Mridangam", "Tabla", "Nadaswaram"));
            q4.setCorrectOptionIndex(2);
            q4.setExplanation("Tabla is a North Indian percussion instrument, not traditionally associated with Tamil music.");
            q4.setDifficulty(QuizQuestion.Difficulty.MEDIUM);
            q4.setCategory(QuizQuestion.Category.MUSIC);
            
            QuizQuestion q5 = new QuizQuestion();
            q5.setQuestionText("Which Tamil poet is known as 'Mahakavi' (great poet)?");
            q5.setOptions(Arrays.asList("Bharathidasan", "Subramania Bharati", "Avvaiyar", "Thiruvalluvar"));
            q5.setCorrectOptionIndex(1);
            q5.setExplanation("Subramania Bharati is known as 'Mahakavi' and is considered one of the greatest Tamil literary figures of the modern era.");
            q5.setDifficulty(QuizQuestion.Difficulty.HARD);
            q5.setCategory(QuizQuestion.Category.LITERATURE);
            
            quizQuestionRepository.saveAll(List.of(q1, q2, q3, q4, q5));
        }
        
        // Add sample forum posts if none exist
        if (forumPostRepository.count() == 0) {
            User admin = userRepository.findByUsername("admin").orElseThrow();
            
            ForumPost post1 = new ForumPost();
            post1.setTitle("Introduction to Tamil Classical Music");
            post1.setContent("Hello everyone! I'm new to Tamil classical music and would love to learn more about it. Can anyone recommend some good resources or artists to start with? I'm particularly interested in understanding the different ragas and talas.");
            post1.setAuthor(admin);
            
            ForumPost post2 = new ForumPost();
            post2.setTitle("Traditional Tamil Recipes");
            post2.setContent("I'm trying to learn more about traditional Tamil cuisine. Does anyone have authentic recipes for dishes like Chettinad Chicken or Kuzhambu that they'd be willing to share? I'd love to try cooking these at home!");
            post2.setAuthor(admin);
            
            ForumPost post3 = new ForumPost();
            post3.setTitle("Tamil Literature Recommendations");
            post3.setContent("I've recently become interested in Tamil literature and would appreciate recommendations for translated works. I'm particularly interested in ancient Tamil poetry and modern novels. Any suggestions would be greatly appreciated!");
            post3.setAuthor(admin);
            
            forumPostRepository.saveAll(List.of(post1, post2, post3));
            
            // Add comments to the first post
            ForumComment comment1 = new ForumComment();
            comment1.setContent("I would recommend starting with T.M. Krishna's concerts. He explains the concepts very well for beginners.");
            comment1.setAuthor(admin);
            comment1.setPost(post1);
            
            ForumComment comment2 = new ForumComment();
            comment2.setContent("The book 'Carnatic Music: A Primer' by Sriram V is an excellent resource for beginners.");
            comment2.setAuthor(admin);
            comment2.setPost(post1);
            
            post1.getComments().addAll(List.of(comment1, comment2));
            forumPostRepository.save(post1);
        }
    }
}
