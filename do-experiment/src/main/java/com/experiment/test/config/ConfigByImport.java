package com.experiment.test.config;

import com.experiment.test.config.imports.Import1Service;
import com.experiment.test.config.imports.Import2Service;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import({Import1Service.class, Import2Service.class})/*  导入普通配置类 */
@Component /* 声明为配置类 */
public class ConfigByImport {
}
