**Upload app browserStack**
curl -u "YOUR_USERNAME:YOUR_ACCESS_KEY" \
-X POST "https://api-cloud.browserstack.com/app-automate/upload" \
-F "file=@/path/to/app/file/Application-debug.apk" \
-F "custom_id=CalculatorApp"

**response app upload**
{
"app_url":"bs://f7c874f21852ba57957a3fdc33f47514288c4ba4",
"custom_id":"CalculatorApp",
"shareable_id":"exampleuser/CalculatorApp"
}
Use "custom_id" to specify the app: (in file browserstack.yml)
** appium --address 127.0.0.1 --port 4723 --base-path /wd/hub **
**Cách setup reports**
1. Tạo biến môi trường JAVA_HOME
2. Thêm biến môi trường Allure-2.14.0 vào máy tính như cài đặt biến môi trường Java (link đến tận folder bin của allure)
3. Link download theo phiên bản: https://mvnrepository.com/artifact/io.qameta.allure/allure-testng
4. Tiếp theo, giải nén ra để nó vào ổ C (ổ chứa hệ điều hành) để tạo biến môi trường trong máy ổn định.
5. Tạo biến môi trường trong biến Path với %allure_home%\bin
   **Cách chạy reports:**
1. Mở terminal trên intellij
2. Chạy lệnh: allure serve target/allure-results
   //  allure generate --single-file target/allure-results --clean      để ghi đè lên file cũ

**Run mvn**
1. mvn test -P login-test