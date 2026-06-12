package com.experiment.test.config.imports;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/* 通过ImportSelector子类的方式导入配置类 */
public class ByImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{ByImportSelectorService.class.getName()};
	}
}
