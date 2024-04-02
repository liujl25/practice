package com.liujunlong.controller;

import com.liujunlong.dao.CoureseMapper;
import com.liujunlong.dao.ScoreMapper;
import com.liujunlong.dao.StudentMapper;
import com.liujunlong.pojo.Course;
import com.liujunlong.pojo.Score;
import com.liujunlong.pojo.Student;
import com.liujunlong.util.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>测试 jdbc 批量插入数据
 *
 * @author liujunlong
 * @date 2023/4/29 13:37
 */
@RestController("/student")
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private CoureseMapper coureseMapper;

    @Autowired
    private TransactionTemplate txTemplate;

    @GetMapping("/list/{num}")
    public List<Student> getStudents(@Valid @PathVariable("num") @Max(5) Integer num) {
        Connection conn = JdbcUtil.initConnection();
        ArrayList<Student> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select * from student");
            //设置每次读取的数量，需要开启游标支持
            preparedStatement.setFetchSize(num);
            preparedStatement.setFetchDirection(ResultSet.FETCH_FORWARD);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            Student student;
            while (resultSet.next()) {
                student = new Student();
                student.setSid(resultSet.getLong(1));
                student.setName(resultSet.getString(2));
                student.setAge(resultSet.getInt(3));
                student.setPhone(resultSet.getString(4));
                student.setAddress(resultSet.getString(5));
                list.add(student);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @PostMapping("/insert/jdbc/{times}")
    public String insertWithJdbc(@Valid @PathVariable("times") @NotNull Integer times) {

        new Thread(() -> {
            try {
                insertTaskJDBC(times);
            } catch (SQLException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }).start();

        return "success";
    }

    private void insertTaskJDBC(Integer times) throws SQLException {

        Connection conn = JdbcUtil.initConnection();
        PreparedStatement cleanStudent = conn.prepareStatement("delete from  student");
        PreparedStatement cleanCourse = conn.prepareStatement("delete from course");
        PreparedStatement cleanSocre = conn.prepareStatement("delete from score");

        PreparedStatement pstmStd = conn.prepareStatement("insert into student(sid,name,age,phone,address) " +
                "values (?,?,?,?,? )");
        PreparedStatement pstmCourse = conn.prepareStatement("insert into course(cid,cname) values(?,?)");
        PreparedStatement pstmScore = conn.prepareStatement("insert into score(sid,cid,score) values (?,?,?)");


        //插入数据前先清空表
        long beginClearTime = System.currentTimeMillis();
        cleanStudent.execute();
        cleanStudent.close();
        cleanCourse.execute();
        cleanCourse.close();
        cleanSocre.execute();
        cleanSocre.close();
        long endClearTime = System.currentTimeMillis();

        System.out.println("清空数据耗时：" + (endClearTime - beginClearTime));

        pstmCourse.setLong(1, 1001L);
        pstmCourse.setString(2, "语文");
        pstmCourse.addBatch();
        pstmCourse.setLong(1, 1002L);
        pstmCourse.setString(2, "数学");
        pstmCourse.addBatch();
        pstmCourse.setLong(1, 1003L);
        pstmCourse.setString(2, "英语");
        pstmCourse.addBatch();
        pstmCourse.executeBatch();
        pstmCourse.close();
        System.out.println("课程写入完毕！");

        long begin = 0;
        long end = begin + 100000;

        //开始总计时
        long bTimeMain = System.currentTimeMillis();


        //学生
        for (int i = 1; i <= times; i++) {
            //开启分段计时，计算10W数据耗时
            long bTimeSub = System.currentTimeMillis();

            while (begin < end) {
                //学生
                pstmStd.setLong(1, begin);
                pstmStd.setString(2, "std" + begin);
                pstmStd.setInt(3, 18);
                pstmStd.setString(4, "13192625655");
                pstmStd.setString(5, "物资大厦401");
                pstmStd.addBatch();
                //分数
                pstmScore.setLong(1, begin);
                pstmScore.setLong(2, 1001L);
                pstmScore.setLong(3, 95);
                pstmScore.addBatch();
                pstmScore.setLong(1, begin);
                pstmScore.setLong(2, 1002L);
                pstmScore.setLong(3, 90);
                pstmScore.addBatch();
                pstmScore.setLong(1, begin);
                pstmScore.setLong(2, 1003L);
                pstmScore.setLong(3, 100);
                pstmScore.addBatch();

                begin++;
            }
            //执行批处理
            pstmStd.executeBatch();
            pstmScore.executeBatch();
            //提交事务
            conn.commit();
            //边界值自增10W
            end += 100000;
            //关闭分段计时
            long eTimeSub = System.currentTimeMillis();
            //输出
            System.out.println("插入第" + i + "个10W条数据耗时：" + (eTimeSub - bTimeSub));
        }
        //关闭总计时
        long eTimeMain = System.currentTimeMillis();
        //输出
        System.out.println("插入" + times * 10 + "w数据共耗时：" + (eTimeMain - bTimeMain));

        pstmStd.close();
        pstmScore.close();
//        conn.close();
    }


    @PostMapping("/insert")
    @Transactional(rollbackFor = Exception.class)
    public String insert() {

        new Thread(() -> insertTask()).start();

        return "success";
    }

    void insertTask() {
        //student
        Student student;
        Course course;
        Score score;

        DefaultTransactionDefinition transDefinition = new DefaultTransactionDefinition();
        TransactionStatus transStatus = txTemplate.getTransactionManager().getTransaction(transDefinition);
        //课程
        course = new Course();
        course.setCid(1L);
        course.setCname("语文");
        coureseMapper.insert(course);
        course = new Course();
        course.setCid(2L);
        course.setCname("数学");
        coureseMapper.insert(course);
        course = new Course();
        course.setCid(3L);
        course.setCname("英语");
        coureseMapper.insert(course);
        System.out.println("课程插入完毕");

        //学生
        for (long i = 0; i < 10000; i++) {

            student = new Student();
            student.setSid(i);
            student.setName("std" + i);
            student.setAge(18);
            student.setPhone("13192625655");
            student.setAddress("物资大厦401");
            studentMapper.insert(student);

            score = new Score();
            score.setSid(i);
            score.setCid(1L);
            score.setScore(95);
            scoreMapper.insert(score);
            score.setSid(i);
            score.setCid(2L);
            score.setScore(90);
            scoreMapper.insert(score);
            score.setSid(i);
            score.setCid(3L);
            score.setScore(96);
            scoreMapper.insert(score);

            if (i % 10000 == 0) {
                System.out.println("当前std:" + i + "，执行事务提交！");
                txTemplate.getTransactionManager().commit(transStatus);
                transStatus = txTemplate.getTransactionManager().getTransaction(new DefaultTransactionDefinition());
            }
        }

    }
}
