package Aplication;


import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class MySql {
    static Connection conn;


    static {
        try {
            conn= DriverManager.getConnection("jdbc:mysql://localhost/gamedatabase","root","Parola123!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public boolean Create(String name,String password) throws SQLException {
    PreparedStatement st=conn.prepareStatement("Insert into user (user_name, password) values(?,?)");
    st.setString(1,name);
    st.setString(2,password);

    return st.execute();
}


public boolean logIN(String name,String password) throws SQLException {
    Statement st= conn.createStatement();

    st.executeQuery("select * from user where user_name=\'"+name+"\' and password=\'"+password+"\'");
    ResultSet rs=st.getResultSet();
if(rs.next()){
    Alert alert= new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText(null);
    alert.setContentText("LogIn Successfully!");
    alert.showAndWait();
    return true;
}
return false;
}

public int Points(String name,String pass) throws SQLException {
    Statement st= conn.createStatement();
    st.executeQuery("select points from user where user_name=\'"+name+"\' and password=\'"+pass+"\'");
    ResultSet rs=st.getResultSet();
    int points = 0;
    if(rs.next()){
       points=rs.getInt(1);
    }
    return points;
}

public boolean Update(int points, String name,String pass) throws SQLException {
        PreparedStatement st= conn.prepareStatement("update user set points=? where user_name=? and password=?");
        st.setString(2,name);
        st.setString(3,pass);
        st.setInt(1,points);

        return st.execute();

}

public boolean image(String name,String pass, File file) throws SQLException, FileNotFoundException {
        PreparedStatement st= conn.prepareStatement("update user set image=? where user_name=? and password=?");
    FileInputStream fileInputStream=new FileInputStream(file);
    st.setString(2,name);
    st.setString(3,pass);
    st.setBinaryStream(1, fileInputStream);

    return st.execute();


}












}
