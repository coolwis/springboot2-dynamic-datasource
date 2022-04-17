package org.springboot.sample.mapper;

        import java.util.List;

        import org.apache.ibatis.annotations.Mapper;
        import org.springboot.sample.entity.Student;

/**
 * StudentMapper , the mapping SQL Statement interface, no logical implementation
 *
 * @author  ChanHongYu (365384722)
 * @myblog http://blog.csdn.net/catoop/
 * @create 2016 years 1 month 20 day
 */
@Mapper
public interface StudentMapper {

    //  annotations  @TargetDataSource  It can't be used here
    List<Student> likeName(String name);
    List<Student> getList();

    Student getById(int id);

    String getNameById(int id);

}

