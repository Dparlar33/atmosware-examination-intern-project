# General Talent Exam Simulation (Work In Progress)

This project is a general talent exam simulation web application developed using the Java programming language. 


## Technologies Used

- **Java Programming Language**: The primary programming language used in the project.
- **Spring Web**: Web framework for Java.
- **Spring Security**: Security framework for Java.
- **OpenFeign**: Feign is a declarative web service client. It makes writing web service clients easier.
- **JWT**: Jwt Service used for authorization and authentication.
- **PostgresSQL**: Relational database management system.
- **JPA**: ORM library for Java.
- **Map structure**: Library used for mapping database models to request/response structures.
- **Postman and Swagger**: Tools used for testing and documenting APIs.



#  Project Requirements

#### User Story: **Organizasyonların kendi sorularını kaydedebileceği, testlerini oluşturabileceği ve bu teste adaylarını davet edebileceği bir Genel Yetenek Testi yapısının oluşturulması beklenmektedir**.

##  Proje Detayları

- Admin ve Organizasyon olacak şekilde 2 yönetim kullanıcısı olacak.
- Admin kulllanıcısı yeni organizasyon oluşturma, var olan organizasyonları listeleme, soru havuzuna
anonim soru ekleme (Sorularda onu ekleyen organizasyonId’si null olursa anonim soru olmakta),
bütün sorular üzerinde silme ve güncelleme yetkisine sahip olma, organizasyon silme ve bilgilerini
güncelleme, test oluşturma ve bu test üzerinde bilgileri güncelleme gibi yetkilerinin tamamına sahip
kullanıcıdır.
- Organizasyon kullanıcısı Admin tarafından oluşturulmuş kendi sorularını ekleyebilen, sadece kendi
soruları üzerinde silme ve güncelleme yetkilerine sahip olan. Kendi testlerini oluşturabilen ve bu test
üzerinde update işlemleri yapabilecek, teste eklemek için kendi soruları ve admin tarafından havuza
eklenmiş olan soruları kullanabilecek düzeyde yetkilere sahip, kendi username ve parolasını
değiştirebilecek ve testlerine mail yoluyla aday daveti yapabilecek yetkilere sahip kullanıcıdır.
- Güvenlik akışları jwt token üzerinden yürütülebilir
- Mikroservices mimarisi kullanımı isteğe bağlı ama kullanımı tercih edilmektedir
- Tercihe bağlı olarak usecase driven design kullanılabilir.
- Mikroservices’ler arası habeleşme protokolünde rest kullanılabilir ama en az 1 tane grpc protokolü
  kullanımı tercihler arasında
- Exception handling
- Logging
- Monitoring (Prometheus Grafana)
- Zaman yeterli olursa unit testler beklenmektedir

**`Management Service:`**
**Yönetimsel olan bütün işlemler bu servis üzerinden yapılacaktır.**
- Adminin organizasyon eklemesi
- adminin kendi parolasını ve kullanıcı adını güncelleyebilmesi
- organizasyonun kendi bilgileri güncelleyebilmesi
- Organizasyon kendi davet listesi ile candidate’lerine devet maili atması ( bu mock bir servis olabilir
mail atılmış gibi yapılıp log düşülmesi yeterlidir)


**`Question Service:`**
**Question ve Option ile alakalı bütün işlemlerin yapılacağı servistir.
Soru ekleme, soru silme, soru güncelleme gibi admin ve organizasyonun yapabileceği sorular
üzerindeki bütün işlemler bu servis üzerinden yapılmaktadır.**

#### Olması beklenenler isterler:

- Admin bütün soruları silebilecek yetkidedir organizasyon sadece kendi sorularını silebilir.
- Admin bütün soruları güncelleyebilecek yetkidedir organizasyon sadece kendi sorularını
güncelleyebilir.
- Soru hali hazırda başlamış yada bitmiş bir testte kullanılmışsa bu soru herkes tarafından
güncellemeye kapatılmalıdır
- bir soru birden fazla testte kullanılabilir ama bir testte aynı soru sadece 1 defa kullanılabilir.
- bir soru en az 2 en fazla 5 seçeneğe sahip olabilir
- question text maksimum 2000 bin karakter option text maksimum 500 karakter aralığında olmalıdır
Nice To Have
- Sorulara ve cevaplara image desteği verilmesi (random alınmış url’ler kullanılabilir) (Önemli: Soruda
ve cevapta aynı anda hem text hemde image null yada blank olamaz)
- Soruların birden fazla doğru cevabı olabilir

**`Exam Service:`**
**Test ile alakalı bütün işlemlerin yapılacağı servistir**

##### Olması beklenenler isterler:

- Admin ve organizasyon bir Test create edebilecek
- Admin ve organizasyon teste kurallar ekleyebilecek (örnek: Kamera kullanımı zorunludur, mikrofon
açık olmalıdır)
- Admin henüz başlamamış bütün Testlere soru eklemesi yapabilir, Organizasyon henüz başlamamış
kendi testine soru eklemesi ve çıkarması yapabilir.
- Admin henüz başlamamış bütün Testler üzerinde Organizasyon ise sadece başlamamış olan kendi
testleri üzerinde güncelleme yapabilir.
- Admin başlamış ama bitmemiş olan bütün testler üzerinde bitiş tarihini uzatabilir, Organizasyon ise
sadece kendi başlamış ama bitmemiş olan testleri üzerinde bitiş tarihini uzatabilir.