package com.experiment.test.config;

import com.experiment.test.config.imports.ByImportSelector;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import({ByImportSelector.class})/*  导入ImportSelector子类配置类 */
@Component /* 声明为配置类 */
public class ConfigByImportSelector {
}
