package com.project.hibernate.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductChoices {

    public List getType(){
        List<String> typeChoices = new ArrayList<String>();
        typeChoices.add("Продукт");
        typeChoices.add("Решение");
        return typeChoices;
    }

    public List getProductCustomers() {
        List<String> productCustomersChoices = new ArrayList<>();
        productCustomersChoices.add("Клиент1");
        productCustomersChoices.add("Клиент2");

        return productCustomersChoices;
    }

    public List getProductAutomationArea() {
        List<String> automationAreaChoices = new ArrayList<>();
        automationAreaChoices.add("Банки, финансовые и страховые компании");
        automationAreaChoices.add("Образование, наука, культура, спорт");
        automationAreaChoices.add("Органы государственной власти, муниципального управления, государственные учреждения");
        automationAreaChoices.add("Предприятия топливно-энергетического комплекса");
        automationAreaChoices.add("Промышленные и строительные предприятия");
        automationAreaChoices.add("СМИ, издательства, реклама");
        automationAreaChoices.add("Телекоммуникации, связь, информационные технологии");
        automationAreaChoices.add("Транспорт, торговля, сфера услуг");

        return automationAreaChoices;
    }

    public List getCentralization() {
        List<String> centralizationChoices = new ArrayList<>();
        centralizationChoices.add("Централизация1");
        centralizationChoices.add("Централизация2");

        return centralizationChoices;
    }

    public List getWork() {
        List<String> workChoices = new ArrayList<>();
        workChoices.add("Работа с персональными данными1");
        workChoices.add("Работа с персональными данными2");

        return workChoices;
    }

    public List getLanguages() {
        List<String> languagesChoices = new ArrayList<>();
        languagesChoices.add("Java");
        languagesChoices.add("PHP");

        return languagesChoices;
    }

    public List getDatabase() {
        List<String> databaseChoices = new ArrayList<>();
        databaseChoices.add("MySQL");
        databaseChoices.add("PostgreSQL");

        return databaseChoices;
    }

    public List getTools() {
        List<String> toolsChoices = new ArrayList<>();
        toolsChoices.add("Средства реализации логики приложений1");
        toolsChoices.add("Средства реализации логики приложений2");

        return toolsChoices;
    }

    public List getEncryptions() {
        List<String> encryptionsChoices = new ArrayList<>();
        encryptionsChoices.add("Использования средств криптозащиты1");
        encryptionsChoices.add("Использования средств криптозащиты2");

        return encryptionsChoices;
    }

    public List getProductDirection() {
        List<String> productDirectionChoices = new ArrayList<>();
        productDirectionChoices.add("Автоматизированные системы управления технологическими процессами");
        productDirectionChoices.add("Банковские технологии");
        productDirectionChoices.add("Инженерные системы");
        productDirectionChoices.add("Интеграция");
        productDirectionChoices.add("ИТ-хостинг");
        productDirectionChoices.add("Облачные технологии");
        productDirectionChoices.add("Облачные услуги");
        productDirectionChoices.add("Обучение");
        productDirectionChoices.add("Проектирование информационных систем");
        productDirectionChoices.add("Разработка программного обеспечения");
        productDirectionChoices.add("Управление бизнес-процессами");
        productDirectionChoices.add("Управление документами");
        productDirectionChoices.add("Управление жизненным циклом продукции");
        productDirectionChoices.add("Управление проектами");
        productDirectionChoices.add("Управление ресурсами предприятия");
        productDirectionChoices.add("Управление эффективностью бизнеса");
        productDirectionChoices.add("Финансовый и управленческий консалтинг");

        return productDirectionChoices;
    }

    public List getArchitecture() {
        List<String> architectureChoices = new ArrayList<>();
        architectureChoices.add("Архитектура1");
        architectureChoices.add("Архитектура2");

        return architectureChoices;
    }
}
