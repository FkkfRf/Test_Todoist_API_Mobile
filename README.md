# Проект по автоматизации тестирования сайта Todoist


<code><img width="50%" title="GitHub" src="images/icons/Todoist-logo.png"></code>
#### © Doist Inc.

# :closed_book:    Содержание

> - [Технологии и инструменты](#toolbox-технологии-и-инструменты)
>
> - [Реализованы проверки](#chart_with_upwards_trend-реализованы-проверки)
>
> - [Запуск тестов в Jenkins](#triangular_flag_on_post-запуск-тестов-в-jenkins)
>
> - [Отчет о результатах тестирования в Allure Report](#triangular_flag_on_post-отчет-о-результатах-тестирования-в-allure-report)
>
> - [Интеграция с Allure TestOps](#triangular_flag_on_post-интеграция-с-allure-testops)
>
> - [Уведомления в Telegram с использованием бота](#triangular_flag_on_post-уведомления-в-telegram-с-использованием-бота)
>

## :toolbox: Технологии и инструменты

<p  align="center"

<code><img width="4%" title="GitHub" src="images/logo/GitHub-logo.svg"></code>
<code><img width="4%" title="IntelliJ IDEA" src="images/logo/IntelijIDEA-logo.svg"></code>
<code><img width="4%" title="Java" src="images/logo/Java-logo.svg"></code>
<code><img width="4%" title="Selenide" src="images/logo/Selenide-logo.svg"></code>
<code><img width="4%" title="Gradle" src="images/logo/Gradle-logo.svg"></code>
<code><img width="4%" title="Junit5" src="images/logo/JUnit5-logo.svg"></code>
<code><img width="4%" title="RestAssured" src="images/logo/RestAssured-logo.svg"></code>
<code><img width="4%" title="Allure Report" src="images/logo/AllureReport-logo.svg"></code>
<code><img width="4%" title="Allure TestOps" src="images/logo/AllureTO-logo.svg"></code>
<code><img width="4%" title="Jenkins" src="images/logo/Jenkins-logo.svg"></code>
<code><img width="4%" title="Telegram" src="images/logo/Telegram-logo.svg"></code>
</p>

> - *В данном проекте использовались:*
>- *<code><strong>*Java*</strong></code> с использованием фреймворка <code><strong>*Selenide*</strong></code> -
   автотесты для UI*
>- *<code><strong>*Gradle*</strong></code> - сборка проекта*
>- *<code><strong>*JUnit 5*</strong></code> - фреймворк для модульного тестирования*
>- *<code><strong>*Jenkins*</strong></code> - запуск тестов*
>- *<code><strong>*RestAssured*</strong></code> - Java-библиотека для тестирования REST API.*
>- *<code><strong>*Allure Report*</strong></code> - визуализации результатов тестирования*
>- *<code><strong>*Allure TestOps*</strong></code> - управление тестами*
>- *<code><strong>*Telegram Bot*</strong></code> - уведомление о результатах тестирования*

## :chart_with_upwards_trend: Реализованы проверки

### API

#### ✓ Тесты

> - [x] *Добавить проект*
>- [x] *Изменить имя последнего созданного проекта*
>- [x] *Удалить последний созданный проект*
>- [x] *Добавить задачу в новый проект*
>- [x] *Удалить задачу в новом проекте*

## :triangular_flag_on_post: Запуск тестов в Jenkins

<img width="4%" title="Jenkins" src="images/logo/Jenkins-logo.svg"> [Сборка в Jenkins](https://jenkins.autotests.cloud/job/C15-FkkfRf-Test-Todoist/)

При настройке параметров запуска в Jenkins сразу же определяем интеграцию с проектом в TestOps:

<p align="center">
  <img src="images/screenshots/JenkinsParam.PNG" alt="job">
</p>

**Cкрипт запуска** из Jenkins:

> clean
>
> test

### Основная страница проекта в Jenkins

<p align="center">
  <img src="images/screenshots/JenkinsMain.PNG" alt="job">
</p>

После выполнения сборки, в блоке <code><strong>*История сборок*</strong></code> напротив номера сборки появится
значок <img width="2%" title="Allure Report" src="images/logo/AllureReport-logo.svg"><code><strong>*Allure
Report*</strong></code>, кликнув по которому, откроется страница с сформированным html-отчетом.

## :triangular_flag_on_post: Отчет о результатах тестирования в Allure Report

<img width="4%" title="Allure Report" src="images/logo/AllureReport-logo.svg"> [Allure Report](https://jenkins.autotests.cloud/job/C15-FkkfRf-Test-Todoist/allure/) 

#### ✓ Главная страница Allure-отчета содержит следующие информационные блоки:

> <code>*ALLURE REPORT*</code> - отображает дату и время прохождения теста, общее количество прогнанных кейсов, а также
> диаграмму с указанием процента и количества успешных, упавших и сломавшихся в процессе выполнения тестов

> <code>*TREND*</code> - отображает тренд прохождения тестов от сборки к сборке

> <code>*SUITES*</code> - отображает распределение результатов тестов по тестовым наборам

> <code>*ENVIRONMENT*</code> - отображает тестовое окружение, на котором запускались тесты (в данном случае информация
> не задана)

> <code>*CATEGORIES*</code> - отображает распределение неуспешно прошедших тестов по видам дефектов

> <code>*FEATURES BY STORIES*</code> - отображает распределение тестов по функционалу, который они проверяют

> <code>*EXECUTORS*</code> - отображает исполнителя текущей сборки (ссылка на сборку в Jenkins)

<p align="center">
  <img src="images/screenshots/Allure1.PNG" alt="Allure Report" >
</p>

#### ✓ В разделе Behaviors тесты отображаются  сгруппироваными в многоуровневый список:

<p align="center">
  <img src="images/screenshots/Allure2.PNG" alt="Allure Report">
</p>

## :triangular_flag_on_post: Интеграция с Allure TestOps

<img width="4%" title="Allure TestOPS" src="images/logo/AllureTO-logo.svg"> [Allure TestOps](https://allure.autotests.cloud/project/1827/launches) 

#### ✓ Lanches
Вся информация о результатах запуска тестов хранится в разделе **Launchs**.

<p align="center">
  <img src="images/screenshots/TestOpsLanches.PNG" alt="dashboards"">
</p>

#### ✓ Тест-кейсы

Тест-кейсы автоматически создаются на основе списка тестов, в AllureReport
<p align="center">
  <img src="images/screenshots/TestOpsTestCases.PNG" alt="test cases">
</p>

## :triangular_flag_on_post: Уведомления в Telegram с использованием бота

После завершения сборки специальный бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет
сообщение с отчетом о прогоне тестов.

<p align="left">
<img width="4%" title="Telegram" src="images/logo/Telegram-logo.svg">
</p>
<p align="center">
<img title="Telegram Notifications" src="images/screenshots/Notification.PNG">
</p>



