# Modul_10_TimeServlet
# Проект TimeServlet

Цей проект - проста веб-аплікація на Java, створена за допомогою Apache Tomcat. Вона містить сервлет, який повертає поточний час у вказаному часовому поясі, і фільтр, який валідує параметр часового поясу.

## Функціональні можливості

1. **TimeServlet**: Сервлет, який обробляє GET-запити до `/time` і повертає поточний час у вказаному часовому поясі.
2. **TimezoneValidateFilter**: Фільтр, який валідує параметр `timezone` і повертає помилку, якщо часовий пояс є некоректним.
