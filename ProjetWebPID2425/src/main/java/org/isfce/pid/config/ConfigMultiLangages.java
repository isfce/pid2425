package org.isfce.pid.config;


import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * Configuration pour définir la spécification de la locale en fonction du
 * paramètre "lang" et d'un coockie "maLocale"
 * 
 * @author Didier
 *
 */
@Configuration
public class ConfigMultiLangages implements WebMvcConfigurer{
	@Bean
	LocaleResolver localeResolver() {
		CookieLocaleResolver clr = new CookieLocaleResolver("maLocale");
		clr.setDefaultLocale(Locale.of("fr")); 
		//clr.setCookieName("maLocale");
		return clr;
	}

	/**
	 * Crée un LCI qui rajoute un paramètre lang aux URL pour changer la locale
	 * 
	 * @return
	 */
	@Bean
	LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	/**
	 * Introduit le LCI
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	
}
