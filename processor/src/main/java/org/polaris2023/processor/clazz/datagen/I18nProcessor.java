package org.polaris2023.processor.clazz.datagen;

import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.language.I18nEnum;
import org.polaris2023.annotation.language.I18nEnumInner;
import org.polaris2023.annotation.language.Other18n;
import org.polaris2023.annotation.language.PotionI18n;
import org.polaris2023.processor.InitProcessor;
import org.polaris2023.processor.clazz.ClassProcessor;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.HashMap;
import java.util.Map;

public class I18nProcessor extends ClassProcessor {

    public static final Map<String, StringBuilder> LANGUAGES =  new HashMap<>();

    public static void add(String key, String value) {
        if (!LANGUAGES.containsKey(key)) LANGUAGES.put(key, new StringBuilder());
        LANGUAGES.get(key).append("\t\t\t").append(value).append("\n");
    }

    public I18nProcessor(JavacProcessingEnvironment environment) {
        super(environment);
    }

    @Override
    public void classDef(TypeElement typeElement) {
        for (Element element : typeElement.getEnclosedElements()) {
            if (element.getKind().isField()) {
                VariableElement variableElement = (VariableElement) element;
                I18n i18n = variableElement.getAnnotation(I18n.class);
                I18nEnum i18nE = variableElement.getAnnotation(I18nEnum.class);
                PotionI18n potionI18n = variableElement.getAnnotation(PotionI18n.class);
                if (i18nE != null) {
                    for (Element element1 : typeElement.getEnclosedElements()) {
                        I18nEnumInner i18nEI = element1.getAnnotation(I18nEnumInner.class);
                        if (i18nEI != null && !element1.getModifiers().contains(Modifier.STATIC)) {
                            String name = typeElement.getQualifiedName() + "." + variableElement.getSimpleName() + "." + element1.getSimpleName();
                            InitProcessor.languageGen(context, "lang(\"en_us\", %s, \"%s\");".formatted(
                                    name,
                                    i18nE.type().equals(I18nEnum.Type.PREFIX) ? i18nE.en_us() + i18nEI.en_us() : i18nEI.en_us() + i18nE.en_us()
                            ));
                            InitProcessor.languageGen(context, "lang(\"zh_cn\", %s, \"%s\");".formatted(
                                    name,
                                    i18nE.type().equals(I18nEnum.Type.PREFIX) ? i18nE.zh_cn() + i18nEI.zh_cn() : i18nEI.zh_cn() + i18nE.zh_cn()
                            ));
                            InitProcessor.languageGen(context, "lang(\"zh_tw\", %s, \"%s\");".formatted(
                                    name,
                                    i18nE.type().equals(I18nEnum.Type.PREFIX) ? i18nE.zh_tw() + i18nEI.zh_tw() : i18nEI.zh_tw() + i18nE.zh_tw()
                            ));
                            Other18n[] other = i18nE.other();
                            Other18n[] other1 = i18nEI.other();
                            for (Other18n other18n : other) {
                                for (Other18n n : other1) {
                                    if (other18n.value().equals(n.value())) {
                                        InitProcessor.languageGen(context, "lang(\"%s\", %s, \"%s\");".formatted(
                                                other18n.value(),
                                                name,
                                                i18nE.type().equals(I18nEnum.Type.PREFIX) ? other18n.translate() + n.translate() : n.translate() + other18n.translate()
                                        ));
                                    }
                                }
                            }
                        }
                    }
                }
                if (i18n != null) {
                    String name;
                    if(i18n.descriptionId().isEmpty()) {
                        name = typeElement.getQualifiedName() + "." + variableElement.getSimpleName();
                    } else {
                        name = "\"" + i18n.descriptionId() + "\"";
                    }
                    InitProcessor.languageGen(context, "lang(\"en_us\", %s, \"%s\");".formatted(
                            name,
                            i18n.en_us()
                    ));
                    InitProcessor.languageGen(context, "lang(\"zh_cn\", %s, \"%s\");".formatted(
                            name,
                            i18n.zh_cn()
                    ));
                    InitProcessor.languageGen(context, "lang(\"zh_tw\", %s, \"%s\");".formatted(
                            name,
                            i18n.zh_tw()
                    ));
                    for (Other18n other18n : i18n.other()) {
                        InitProcessor.languageGen(context, "lang(\"%s\", %s, \"%s\");".formatted(
                                other18n.value(),
                                name,
                                other18n.translate()
                        ));
                    }
                } else if(potionI18n != null) {
                    String name;
                    name = typeElement.getQualifiedName() + "." + variableElement.getSimpleName();
                    InitProcessor.languageGen(context, "addPotion(\"en_us\", %s, \"%s\", \"%s\", \"%s\", \"%s\");".formatted(
                            name,
                            potionI18n.en_us(),
                            PotionI18n.PREFIX_SPLASH_EN_US,
                            PotionI18n.PREFIX_LINGERING_EN_US,
                            PotionI18n.SUFFIX_EN_US
                    ));
                    InitProcessor.languageGen(context, "addPotion(\"zh_cn\", %s, \"%s\", \"%s\", \"%s\", \"%s\");".formatted(
                            name,
                            potionI18n.zh_cn(),
                            PotionI18n.PREFIX_SPLASH_ZH_CN,
                            PotionI18n.PREFIX_LINGERING_ZH_CN,
                            PotionI18n.SUFFIX_ZH_CN
                    ));
                    InitProcessor.languageGen(context, "addPotion(\"zh_tw\", %s, \"%s\", \"%s\", \"%s\", \"%s\");".formatted(
                            name,
                            potionI18n.zh_tw(),
                            PotionI18n.PREFIX_SPLASH_ZH_TW,
                            PotionI18n.PREFIX_LINGERING_ZH_TW,
                            PotionI18n.SUFFIX_ZH_TW
                    ));

                }
            }
        }
    }
}
