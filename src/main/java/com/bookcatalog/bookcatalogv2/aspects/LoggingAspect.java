package com.bookcatalog.bookcatalogv2.aspects;


/**
 * Cross cutting concren
 * => Perhatian Lintas Sektor dalam dunia pemograman bisa
 * diartikan banyak fungsi yang digunakan di banyak titik aplikasi atau semua fugsionalitas
 * yang mempengaruhi di banyak titik dari applikasi.
 *
 * apa itu Aspect ?
 * => adalah sebuah potongan code yang nantinya di exsekusi oleh suatu framework ketika sebuah
 * method yang di tentukan dipanggil
 *
 * apa itu AOP ?
 * => sebuah proses yang memisahkan cross cutting concren dari bisnis logic atau
 * fungsionalistas utama dari sebuah services tujuanya adalah untuk meningkatkan modularitas
 * dari sebuah applikasi hal ini dilakukan dengan mendeklarasikan aspec yang nantinya aspec ini
 * akan melakukan alter alias mengubah beavior atau perilaku dari base code nya base code disini
 * merujuk pada service yang menagani suatu fungsionalitas nya.
 * caranya dengan meng applay sebuah advice kepada sebuah join point tertentu yang ditentukan
 * oleh poincut.
 *
 * keuntungan menggunakan AOP :
 * 1.akan memodularisasi cross cutting concerent karna croscutting concrent akan di modularkan
 *   atau disatukan menjadi sebuah aspect. karna menjadi modular maka ketiaka terjadi perubahan
 *   aspec dapat dilakukan secara tercentralisasi didalam 1 file
 * 2.modul service menjadi lebih bersih karna hganya diisi dengan primary concrent nya(core bisnis logic/core functionality)
 *
 *
 * Fungsi AOP
 * => untuk memisahkan Potongan kode yang bersifat boilplater sehinga suatu class atau service
 * hanya berisi dari core fungsionality nya
 *
 * bebrapa fitur yang siselesaikan dengan AOP :
 * - Logging
 * - data validation
 * - catching
 * - security
 * - internationalization
 * - dll
 *
 * === TERMINOLOGI AOP ===
 * 1. aspec seperti apa yang ingin kamu buat aspec disini merujuk kepada potongan code yang akan
 *    dijalankan oleh framwork dikala sebuah method yang terlah ditentukan dieksekusi
 * 2. advice ini merujuk kapan aspec yang kamu buat akan dieksekusi apakah sebelum method yang
 *    ditentukan dieksekusi atau sesudah method yang sitentukan dieksekusi dll
 * 3. poincut ini merujuk kepada method mna yang ingin di intrcept oleh aspec yang terlah dibuat
 *    atau pointcut ini adalah fungsi yang mentrigerr aspec yang kamu buat.
 * 4. joinpoint ini adalah event yang menteriger dari suatu aspec dalam spring framework
 *    joinpoin meruspakan pemanggilan sebuiah method.
 * 5. target object yang merujuk kepada been yang ada pada container spring yang mendeklarasikan
 *    method yang nantinya akan di intercept oleh sebuh aspect
 *
 * Untuk mengguakan spring Aspect Oriented Programing kita harus meng enable spring AOP dengan cara
 * memberi annotasi @EnableAspecJAutoProxy pada main class dan kita mengannotasi juga kelas yang
 * nantinya akan dijadikan sebagai AOP dengan memberi annotasi @Aspect dan agar dikelolah oleh spring
 * container kelas nya juga harus di annotasi @Component/@Configuration
 *
 * untuk membuat pointcut kita harus membuat suatu method baru yang mna method tersebut akan
 * menjadi Pointcut dari aspect yang kita buat
 */

 /*
  *untuk membuat pointcut kita harus membuat suatu method baru yang mna method tersebut akan
  * menjadi Pointcut dari aspect yang kita buat
  */

    /**
     * @Pointcut("execution()")
     * parameter "execution()" dalah salah satu pointcut desigennater yang umum digunakan
     * execution() => ini berarti kita memerintahkan kepada framework untuk melakukan trigerr saat
     * terdapat eksekusi dari sebuah method yang nanti nya kita deklarasikan didalam argumen poincut designnater ini (execution())
     * saat kita membuat execution() kita harus menentukan return dari execution tersebut
     * jika kita tidak terlalu memperdulikan dengan tipe return nya kita bisa beritanda asteric (*) pada
     * argumen dari execution()
     * contoh :
     * execution(*)
     * setelah intu kita akan definisikan alamat dari method tersebut denga base package
     * beserta nama mehod yang akan ditarget
     * contoh :
     * execution(* com.bookcatalog.bookcatalogv2.controllers.BookController.findBookDetail)
     * setelah itu kita tinggal tentukan criteria dari argumen method tersebut jika kita tidak
     * menentukan criteria argumen nya atau argumen nya bisa apa saja maka kita bisa menuliskanya
     * dengan tanda kurung () dan kita tambahkan titik dua kali
     * contioh :
     * (..)
     * execution(* com.bookcatalog.bookcatalogv2.controllers.BookController.findBookDetail(..))
     *
     * jika kita ingin semua method di beru aop maka kita cukup tuliskan base package samapi nama Class nya saja dan di ikuti dengan (.*)
     * contoh : @Pointcut("execution(* com.bookcatalog.bookcatalogv2.controllers.AuthorResource.*(..))")/Designator_execution.java
     *
     * dan jika kita ingin semua class dalam 1 package yang mau di inercep maka kita cukup tuliskan (*.*(..))
     * contoh : @Pointcut("execution(* com.bookcatalog.bookcatalogv2.controllers.*.*(..))")
     */

    /**
     * cara kerja AOP
     * => kunci untama dari AOP adalah ada pada spring container dimna aspec atau pun object class target nya dalam hal ini
     * adalah BookResource harus lah merupakan bean yang dikelola daur hidup nya oleh spring container
     * jadi ketika bean tersebut (BookResource) dijadikan sebuah target maka ketika bean tersebut dibutuhkan untuk di eksekusi
     * maka spring container tidak akan menjalankan bean yang sesungguh nya alih2 dia akan memberikan Proxy Object yang mna
     * Proxy object tersebut salah satunya akan menjalankan aspec logic yang berdasarkan configuration yang telah kita buat sebelumny
     * dan kemudian dia mendelegasikan method yang sebenarnya pendekatan seperti ini biasa disebut weving.
     *
     * what is WEAVING ?
     * => adalah sebuah teknik yang mna aspec akan di combine alias digabungkan oleh bisnis code nya proses ini akan menerapkan
     *    Aspec ke target object nya dengan kemudian membuat sebuah object proxy.
     *    Proses Weaving ini bisa dilakukan pasa saat compile, loadTime, atau bisa juga saat proses runtime nya.
     *    Didalam konsep Spring Weaving ini dikerjaakn saat proses runtime menggunakan proxy patren
     */
                                /***************************                       ******************************
                                 *                         *                       *                            *
    ======================>      * Proxy ke BookResource   *  ===============>     *    BookResource            *
    findBookBySecureId()    >    *                         *   delegates       >   *    findBookBySecureId(){   *
    ======================>      *                         *  ===============>     *        logic method        *
                                 *                         *                       *    }                       *
                                 * findBookBySecureId(){   *                       *                            *
                                 *  logic AOP              *                       *                            *
                                 * }                       *                       ******************************
                                 *                         *
                                 ***************************/

    /**
     * Bebrapa Poincut Desigenator :
     * 1. Execution => untuk mencocokan method yang nantinya kana dijadikan sebagai joinpoin nya
     *    contoh Designator_execution.java
     *
     * 2. Within => digunakan untuk membatasi kriteria joinpoin dengan parameter yang digunakna adalah tipe class. penggunakan nya
     *    ini mirip dengan execution()
     *    contoh pada desigenater/Designator_within.java
     *    Perbedaan antara execution dan within adalah :
     *      dengan menggunkan execution dapat lebih detail dalam menentukan method mna hingga arguman seperti apa yang
     *      inggin dijadikan joinpoint namun pada within pilihanya terbatas hanya sampai level class tidak bisa sampai
     *      level method
     *
     * 3. args => desigenater ini dikusukan untuk mencocokan joinpoin dengan paramerer berupa argumen dari method tersebut
     *    namun penggunakn designator ini biasanya di combine dengan desigenator lainya dan hampir tidak pernah digunkan sendirian
     *    Contoh pada desigenater/Designator_args.java
     *    sebenarnya kita bisa menggunkan desigenator execution() untuk meng hasilkan hasil yang sama dengan args
     *    caranya sebagai berikut :
     *    @Pointcut(execution("* com.bookcatalog.bookcatalogv2.controllers.*.*(com.bookcatalog.bookcatalogv2.dto.PublisherCreaterequestDto)")
     *
     * 4. @args => digunakan untuk membatasi kriteria joinpoin dengan parameternya adalah semua argumen yang mna tipe class nya
     *    mendapatkan sebuah annotasi yang telah ditentukan.
     *    Contoh : desigenater/Designator_Args.java dan BookCreateRequestDto.java
     *
     * 5. @annotation => designater ini mirip dengan @args dan masih ter kait dengan annotasi karna designator @annotation ini
     *    bertujuan untuk membatasi criteria dari joinpoin pada semua method yang diannotasi yang nanti di tentukan oleh designater nya
     *    contoh : desigenater/Designater_annotation.java
     */


    /**
     * Advice
     * => @Before
     *     - aspec method akan dieksekusi sebelum joinpoin dijalankan
     * => @After
     *      - aspec method akan dieksekusi setelah joinpoin dijalankan
     * => @AfterReturnig
     *      - aspec akan dieksekusi setelah joinpoinnya memberikan return
     * => @AfterThrowing
     *      - aspect akan dijalankan setelah joinpoinya menggalami throw exception
     * => @Around
     *      - aspect akan dieksekusi baik sebelum dan sesudah esekusi dari method joinpoin nya. Advice ini adalah
     *        advice yang paling powerful menurut saya karna dengan menggunakan advice in kita dapt menulis sebuah logic
     *        yang membuat aspec seolah olah membungkus dari method joinpoinnya ini seperti menggabungkan advice @After dan @Before secara bersamaan
     *        contoh : /advice/Advice_around.java
     *
     * untuk exp : @Before @After @AfterThrowing @AfterReturnig ada pada : /advice/
     *
     **/

