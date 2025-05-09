package com.experiment.my.auto;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author chenxuegui
 * @since 2025/5/9
 */
public class ImportSelectorAutoConfiguer implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {

		ServiceLoader<AutoConfiguer> load = ServiceLoader.load(AutoConfiguer.class);
		List<String> autoConfiguers = new ArrayList<>();
		Iterator<AutoConfiguer> iterator = load.iterator();
		while (iterator.hasNext()) {
			AutoConfiguer configuer = iterator.next();
			autoConfiguers.add(configuer.getClass().getName());

		}
		return autoConfiguers.toArray(new String[0]);
	}
}
