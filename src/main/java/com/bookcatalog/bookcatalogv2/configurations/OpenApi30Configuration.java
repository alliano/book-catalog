package com.bookcatalog.bookcatalogv2.configurations;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * @OpenApiDefinition => untuk menganotasi bahwa kelas ini adalh
 * kelas open api definition atau kelas ini akan memuat definisi definisi
 * dari api yang kita buat dan juga berisi konfigurasi dari open api yang kita buat
 *
 * parameter info dan annotasi @Info adalah untuk memnuat informasi dari api yang kita buat
 * @SecurityScheme inni annotasi untuk mengkonfigurasi Security pada swager
 * nya (menapilkan fied untuk meng autentikasi JWT)
 */
@Configuration @OpenAPIDefinition(info = @Info(title = "Book catalog API", version = "v1"))
@SecurityScheme(name = "bearerAuth",type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class OpenApi30Configuration {

}
