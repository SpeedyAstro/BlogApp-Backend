package in.astro.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectConfig {

    @Bean
    public Cloudinary getCloudinary(){
        Map map = new HashMap<>();
        map.put("cloud_name","dvqaeehte");
        map.put("api_key","617982441979326");
        map.put("api_secret","zh46RJEuNBiVy4by1F6NJpT9D20");
        map.put("secure",true);
        return new Cloudinary(map);
    }
}
