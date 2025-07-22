# 🧪 Test Automation Framework for av.by

Автоматизированный Java-фреймворк для UI и API тестирования сайта [av.by](https://www.av.by).  
Основан на **JUnit 5**, **Selenium WebDriver**, **REST-assured**, **Log4j 2** и архитектуре **Page Object Model**.

---

## 🧰 Технологии

| Инструмент       | Назначение                     |
|------------------|--------------------------------|
| Java 21          | Язык разработки                |
| Selenium 4.31.0  | UI-автоматизация               |
| JUnit 5.12.1     | Фреймворк тестирования         |
| REST-assured 5.4 | Тестирование API               |
| JavaFaker 1.0.2  | Генерация фейковых данных      |
| Log4j 2.20.0     | Логирование                    |
| Allure-junit5    | Отчётность                     |
| Maven            | Сборщик и менеджер зависимостей |

---

## 🗂 Структура проекта

``` text
src/
├── main/
│   └── java/by/av/
│       ├── api/                  # Логика и вспомогательные API-классы
│       ├── driver/               # WebDriver конфигурация
│       ├── pages/home/           # Page Object'ы для форм авторизации
│       └── utils/                # Утилиты
├── test/
│   └── java/by/av/
│       ├── api/                  # API тесты
│       └── ui/                   # UI тесты
└── pom.xml
```
