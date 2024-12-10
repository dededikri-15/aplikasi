package com.example.aplikasipegawai;

public class DbContract {
    public static final String SERVER_LOGIN_URL = "http://192.168.100.30/loginRegister_Mysql_volley_api_2024/checkLogin.php";
    public static final String SERVER_REGISTER_URL = "http://192.168.100.30/loginRegister_Mysql_volley_api_2024/createData.php";


    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD="http://192.168.100.30/loginRegister_Mysql_volley_api_2024/tambahPgw.php";
    public static final String URL_GET_ALL = "http://192.168.100.30/loginRegister_Mysql_volley_api_2024/tampilSemuaPgw.php";
    public static final String URL_GET_EMP = "http://192.168.100.30/loginRegister_Mysql_volley_api_2024/tampilPgw.php";
    public static final String URL_UPDATE_EMP = "http://192.168.100.30/loginRegister_Mysql_volley_api_2024/updatePgw.php";
    public static final String URL_DELETE_EMP = "http://192.168.100.30/loginRegister_Mysql_volley_api_2024/hapusPgw.php";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAMA = "nama";
    public static final String KEY_EMP_POSISI = "posisi";
    public static final String KEY_EMP_GAJIH = "gaji";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "name";
    public static final String TAG_POSISI = "posisi";
    public static final String TAG_GAJI = "gaji";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id";
}
