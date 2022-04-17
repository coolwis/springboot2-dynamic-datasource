package org.springboot.sample.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springboot.sample.datasource.TargetDataSource;
import org.springboot.sample.entity.Student;
import org.springboot.sample.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Service;

/**
 * Student Service
 *
 * @author   ChanHongYu (365384722)
 * @myblog http://blog.csdn.net/catoop/
 * @create  2016 years 1 month 12 day
 */
@Service
public class StudentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // MyBatis the Mapper Method definition interface
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getListOfMapper(){
        return studentMapper.getList();
    }
//    @TargetDataSource(name="ds2")
//    public List<Student> likeName(String name){
//        return studentMapper.likeName(name);
//    }

//    public List<Student> likeNameByDefaultDataSource(String name){
//        return studentMapper.likeName(name);
//    }

    /**
     *  Use the default data source without specifying the data source
     *
     * @return
     * @author SHANHY
     * @create 2016 years 1 month 24 day
     */
    public List<Student> getList(){
        String sql = "SELECT ID,NAME,SUM_SCORE,age_score, AGE  FROM STUDENT";
        return (List<Student>) jdbcTemplate.query(sql, new RowMapper<Student>(){

            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student stu = new Student();
                stu.setId(rs.getInt("ID"));
                stu.setAge(rs.getInt("AGE"));
                stu.setName(rs.getString("NAME"));
                stu.setSumScore(rs.getString("SUM_SCORE"));
//                stu.setAgeScore(rs.getString("age_score"));
                return stu;
            }

        });
    }

    /**
     *  Specify data source
     *
     * @return
     * @author SHANHY
     * @create 2016 years 1 month 24 day
     */
//    @TargetDataSource(name="ds1")
//    public List<Student> getListByDs1(){
//        String sql = "SELECT ID,NAME,SCORE_SUM,SCORE_AVG, AGE  FROM STUDENT";
//        return (List<Student>) jdbcTemplate.query(sql, new RowMapper<Student>(){
//
//            @Override
//            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Student stu = new Student();
//                stu.setId(rs.getInt("ID"));
//                stu.setAge(rs.getInt("AGE"));
//                stu.setName(rs.getString("NAME"));
//                stu.setSumScore(rs.getString("SCORE_SUM"));
//                stu.setAvgScore(rs.getString("SCORE_AVG"));
//                return stu;
//            }
//
//        });
//    }

    /**
     *  Specify data source
     *
     * @return
     * @author SHANHY
     * @create 2016 years 1 month 24 day
     */
//    @TargetDataSource(name="ds2")
//    public List<Student> getListByDs2(){
//        String sql = "SELECT ID,NAME,SCORE_SUM,SCORE_AVG, AGE  FROM STUDENT";
//        return (List<Student>) jdbcTemplate.query(sql, new RowMapper<Student>(){
//
//            @Override
//            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Student stu = new Student();
//                stu.setId(rs.getInt("ID"));
//                stu.setAge(rs.getInt("AGE"));
//                stu.setName(rs.getString("NAME"));
//                stu.setSumScore(rs.getString("SCORE_SUM"));
//                stu.setAvgScore(rs.getString("SCORE_AVG"));
//                return stu;
//            }
//
//        });
//    }
}

