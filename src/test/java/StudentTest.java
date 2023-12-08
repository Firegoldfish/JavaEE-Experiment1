import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StudentTest {
    @Test
    public void findStudentById() throws IOException{ //根据ID准确查找
        String resource = "mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Student student = sqlSession.selectOne("findStudentById",1);
        System.out.println(student.toString());
        sqlSession.close();
    }
    @Test
    public void findStudentByName() throws IOException{ //根据姓名模糊查询
        String resource = "mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Student> student = sqlSession.selectList("findStudentByName","张");
        for (Student student1:student){
            System.out.println(student1);
        }
        sqlSession.close();
    }
    @Test
    public void findStudentByNameAndAddress() throws IOException{ //根据姓名地址组合查询
        String resource = "mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Student student = new Student();
        student.setName("张三");
        student.setAddress("山西");
        Student student1 = sqlSession.selectOne("findStudentByNameAndAddress",student);
        System.out.println(student1);
        sqlSession.close();
    }
    @Test
    public void insertStudent() throws IOException{ //增
        String resource = "mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Student student = new Student();
        student.setName("李斯");
        student.setAge(19);
        student.setGender("男");
        student.setNumber("17777777777");
        student.setStatus(3);
        student.setAddress("上海");
        int num = sqlSession.insert("insertStudent",student);
        if(num==1) System.out.println("成功新增了1行数据。");
        else System.out.println("增加失败。");
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void deleteStudent() throws IOException{ //删
        String resource = "mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int num = sqlSession.delete("deleteStudent",2);
        if(num==1) System.out.println("成功删除了1行数据。");
        else System.out.println("删除失败。");
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void updateStudent() throws IOException{ //改
        String resource = "mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Student student = new Student();
        student.setName("王八");
        student.setAddress("北京");
        student.setNumber("18888888888");
        student.setId(1);
        student.setStatus(4);
        int num = sqlSession.update("updateStudent",student);
        if(num==1) System.out.println("成功更新了1行数据。");
        else System.out.println("更新失败。");
        sqlSession.commit();
        sqlSession.close();
    }
}
