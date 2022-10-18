package com.bookcatalog.bookcatalogv2.security;

/**
 * ini adalah diagram untuk mengilustrasikan bagaiman spring security bekerja
 *
 *                  disini request akan di
 *                  intercept dulu oleh
 *                  autentication fileter
 *
 *                  ************************                *********************
 *   request        *                      *                *                   *
 *  ----------->    * Autentication filter * ------------>  * Security Context  *
 *                  *                      *                *                   *
 *                  ************************                *********************
 *                       |           ^
 *                       |           |     ket : kemudian autentication filter akan
 *                       |           |           mendelegasikan request tersebut ke sebuah
 *                       |           |           component yang bernama Autentication Managger
 *                       V           |
 *
 *                  *****************************
 *                  *                           *  ket : kemudian Autentication Managger akan menggukan
 *                  *   Autentication Managger  *        Autentication Provider untuk memproses autentikasi
 *                  *                           *        yang diberikan oleh user melalui requst
 *                  *****************************
 *                       |           ^
 *                       |           |
 *                       |           |
 *                       |           |
 *                       V           |
 *
 *                  *****************************           ************************
 *                  *                           *           *                      *
 *                  *   Autentication Provider  * ------->  *   UserDetailSerivce  *
 *                  *                           *           *                      *
 *                  *****************************           ************************
 *
 *                  ket : kemudian di Autentication Provider inilah proses
 *                        Autentikasi dikerjakan ada beberapa komponen yang
 *                        biasanya terlibat di autentication provider misalnya sperti
 *                        UserDetailService yang mna UserDetailService adalah construct
 *                        dari spring security yang mna service ini akan mengelola detail
 *                        dari user mana saja yang berhak melakukan request ke applikasi
 *                        kemudian ada Password encoder yang man Passwod Encoder akan
 *                        mellakukan encription password aggar password yang disimpan didalam
 *                        database berbentuk encription password selain itu juga password encoder
 *                        akan memvalidasi password yang dikirim oleh user ke passwod yang
 *                        tersimpan di dalam database jika password yang dikirimkan user itu valid
 *                        maka hasilnya akan dikembalikan lagi ke Autententication Filter setelah itu
 *                        Autentication Filter akan membuat Security contex yang berisi detail
 *                        dari user yang melakukan autentikasi tersebut
 */