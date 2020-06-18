package shop.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDao extends DaoBase {

    public List<Map<String, Object>> fetchGenreInfo() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> genreInfoList = null;

        try {
            this.connect();
            stmt = con.prepareStatement("SELECT * FROM genre");
            rs = stmt.executeQuery();

            genreInfoList = new ArrayList<>();

            while (rs.next()) {
                Map<String, Object> genreInfoMap = new HashMap<>();
                genreInfoMap.put("genreCode", rs.getInt("genre_code"));
                genreInfoMap.put("genreName", rs.getString("genre_name"));
                genreInfoList.add(genreInfoMap);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return genreInfoList;
    }
}
