
package com.dwyoo.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.dwyoo.spring.dto.BDto;
import com.dwyoo.spring.util.Constant;

// Data Access Object
// 데이터를 접근하기 위핸 클래스
// jsp jdbc
public class BDao {

    DataSource dataSource;

    JdbcTemplate template = null;

    public BDao() {

        // 빈에서 만들어주므로 이제필요없음.
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/myoracle");

        } catch (NamingException e) {
            e.printStackTrace();
        }

        template = Constant.template;
    }

    public ArrayList<BDto> list() {
        String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc;";
        return (ArrayList<BDto>) template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));

        // ArrayList<BDto> dtos = new ArrayList<BDto>();
        // Connection connection = null;
        // PreparedStatement preparedStatement = null;
        // ResultSet resultSet = null;
        //
        // try {
        // connection = dataSource.getConnection();
        // // 그룹넘버로 내림차순, Step 오름차
        // String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc;";
        // preparedStatement = connection.prepareStatement(query);
        // resultSet = preparedStatement.executeQuery();
        // while (resultSet.next()) {
        // int bId = resultSet.getInt("bId");
        // String bName = resultSet.getString("bName");
        // String bTitle = resultSet.getString("bTitle");
        // String bContent = resultSet.getString("bContent");
        // Timestamp bDate = resultSet.getTimestamp("bDate");
        // int bHit = resultSet.getInt("bHit");
        // int bGroup = resultSet.getInt("bGroup");
        // int bStep = resultSet.getInt("bStep");
        // int bIndent = resultSet.getInt("bIndent");
        //
        // BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
        // dtos.add(dto);
        // }
        // } catch (Exception e) {
        // e.printStackTrace();
        // } finally {
        // try {
        // if (resultSet != null)
        // resultSet.close();
        // if (preparedStatement != null)
        // preparedStatement.close();
        // if (connection != null)
        // connection.close();
        // } catch (SQLException e2) {
        // e2.printStackTrace();
        // }
        // }
        //
        // return dtos;
    }

    public void write(final String bName, final String bTitle, final String bContent) {

        template.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "insert into mvc_board(bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values(?, ?, ?, 0, 0, 0, 0);";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, bName);
                preparedStatement.setString(2, bTitle);
                preparedStatement.setString(3, bContent);

                return preparedStatement;
            }
        });

        // Connection connection = null;
        // PreparedStatement preparedStatement = null;
        //
        // try {
        // connection = dataSource.getConnection();
        // String query = "insert into mvc_board(bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values(?, ?, ?, 0, 0, 0, 0);";
        // preparedStatement = connection.prepareStatement(query);
        // preparedStatement.setString(1, bName);
        // preparedStatement.setString(2, bTitle);
        // preparedStatement.setString(3, bContent);
        // int result = preparedStatement.executeUpdate();
        // } catch (Exception e) {
        // e.printStackTrace();
        //
        // } finally {
        // try {
        // if (preparedStatement != null)
        // preparedStatement.close();
        // if (connection != null)
        // connection.close();
        // } catch (SQLException e2) {
        // e2.printStackTrace();
        // }
        //
        // }

    }

    public BDto contentView(String bid) {
        // TODO Auto-generated method stub
        upHit(bid);

        String query = "select * from mvc_board where bid = " + bid;
        
        return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));

        // BDto dto = null;
        // Connection connection = null;
        // PreparedStatement preparedStatement = null;
        // ResultSet resultSet = null;
        //
        // try {
        // connection = dataSource.getConnection();
        // String query = "select * from mvc_board where bid = ?;";
        // preparedStatement = connection.prepareStatement(query);
        // preparedStatement.setInt(1, Integer.parseInt(bid));
        // resultSet = preparedStatement.executeQuery();
        //
        // if(resultSet.next()) {
        // int bId = resultSet.getInt("bId");
        // String bName = resultSet.getString("bName");
        // String bTitle = resultSet.getString("bTitle");
        // String bContent = resultSet.getString("bContent");
        // Timestamp bDate = resultSet.getTimestamp("bDate");
        // int bHit = resultSet.getInt("bHit");
        // int bGroup = resultSet.getInt("bGroup");
        // int bStep = resultSet.getInt("bStep");
        // int bIndent = resultSet.getInt("bIndent");
        //
        // dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
        // }
        //
        // } catch (Exception e) {
        // e.printStackTrace();
        // } finally {
        // try {
        // if (resultSet != null)
        // resultSet.close();
        // if (preparedStatement != null)
        // preparedStatement.close();
        // if (connection != null)
        // connection.close();
        // } catch (SQLException e2) {
        // e2.printStackTrace();
        // }
        // }
        //
        // return dto;
    }

    private void upHit(final String bid) {
        String query = "update mvc_board set bHit = bHit + 1 where bid = ?;";
        template.update(query, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement arg0) throws SQLException {
                arg0.setInt(1, Integer.parseInt(bid));
            }

        });

        // Connection connection = null;
        // PreparedStatement preparedStatement = null;
        //
        // try {
        // connection = dataSource.getConnection();
        // String query = "update mvc_board set bHit = bHit + 1 where bid = ?;";
        // preparedStatement = connection.prepareStatement(query);
        // preparedStatement.setString(1, bid);
        // int result = preparedStatement.executeUpdate();
        // } catch (Exception e) {
        // e.printStackTrace();
        //
        // } finally {
        // try {
        // if (preparedStatement != null)
        // preparedStatement.close();
        // if (connection != null)
        // connection.close();
        // } catch (SQLException e2) {
        // e2.printStackTrace();
        // }
        //
        // }
    }

    public void modify(final String bId, final String bName, final String bTitle, final String bContent) {
        String query = "update mvc_board set bName = ?,bTitle = ?, bContent = ? where bId = ?";

        template.update(query, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, bName);
                preparedStatement.setString(2, bTitle);
                preparedStatement.setString(3, bContent);
                preparedStatement.setInt(4, Integer.parseInt(bId));

            }
        });
        // Connection connection = null;
        // PreparedStatement preparedStatement = null;
        // System.out.println("ddddddd" + bId);
        // try {
        // connection = dataSource.getConnection();
        // String query = "update mvc_board set bName = ?,bTitle = ?, bContent = ? where bId = ?";
        // preparedStatement = connection.prepareStatement(query);
        // preparedStatement.setString(1, bName);
        // preparedStatement.setString(2, bTitle);
        // preparedStatement.setString(3, bContent);
        // preparedStatement.setInt(4, Integer.parseInt(bId));
        // int result = preparedStatement.executeUpdate();
        // } catch (Exception e) {
        // e.printStackTrace();
        //
        // } finally {
        // try {
        // if (preparedStatement != null)
        // preparedStatement.close();
        // if (connection != null)
        // connection.close();
        // } catch (SQLException e2) {
        // e2.printStackTrace();
        // }
        //
        // }
    }

    public void delete(final String bid) {

        String query = "delete from mvc_board where bId = ?";

        template.update(query, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, Integer.parseInt(bid));

            }
        });

        // Connection connection = null;
        // PreparedStatement preparedStatement = null;
        // System.out.println("ddddddd" + bid);
        // try {
        // connection = dataSource.getConnection();
        // String query = "delete from mvc_board where bId = ?";
        // preparedStatement = connection.prepareStatement(query);
        // preparedStatement.setInt(1, Integer.parseInt(bid));
        // int result = preparedStatement.executeUpdate();
        // } catch (Exception e) {
        // e.printStackTrace();
        //
        // } finally {
        // try {
        // if (preparedStatement != null)
        // preparedStatement.close();
        // if (connection != null)
        // connection.close();
        // } catch (SQLException e2) {
        // e2.printStackTrace();
        // }
        //
        // }
    }

    public BDto replay_view(String bid) {
        String query = "select * from mvc_board where bid = " + bid;
        return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));

//        BDto dto = null;
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = dataSource.getConnection();
//            String query = "select * from mvc_board where bid = ?;";
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, Integer.parseInt(bid));
//            resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                int bId = resultSet.getInt("bId");
//                String bName = resultSet.getString("bName");
//                String bTitle = resultSet.getString("bTitle");
//                String bContent = resultSet.getString("bContent");
//                Timestamp bDate = resultSet.getTimestamp("bDate");
//                int bHit = resultSet.getInt("bHit");
//                int bGroup = resultSet.getInt("bGroup");
//                int bStep = resultSet.getInt("bStep");
//                int bIndent = resultSet.getInt("bIndent");
//                System.out.println("dd" + bHit + "," + bGroup + "," + bStep + "," + bIndent);
//                dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (resultSet != null)
//                    resultSet.close();
//                if (preparedStatement != null)
//                    preparedStatement.close();
//                if (connection != null)
//                    connection.close();
//            } catch (SQLException e2) {
//                e2.printStackTrace();
//            }
//        }
//
//        return dto;
    }

    public void reply(String bId, final String bName, final String bTitle, final String bContent, final String bGroup, final String bStep, final String bIndent) {

        replyShape(bGroup, bStep);

        String query = "insert into mvc_board(bName, bTitle, bContent, bGroup, bStep, bIndent) values(?, ?, ?, ?, ?, ?);";
        template.update(query, new PreparedStatementSetter() {
            
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
              preparedStatement.setString(1, bName);
              preparedStatement.setString(2, bTitle);
              preparedStatement.setString(3, bContent);
              preparedStatement.setInt(4, Integer.parseInt(bGroup));
              preparedStatement.setInt(5, Integer.parseInt(bStep) + 1);
              preparedStatement.setInt(6, Integer.parseInt(bIndent) + 1);
            }
        });        
        
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        System.out.println("dd" + bGroup + "," + bStep + "," + bIndent);
//
//        try {
//            connection = dataSource.getConnection();
//            String query = "insert into mvc_board(bName, bTitle, bContent, bGroup, bStep, bIndent) values(?, ?, ?, ?, ?, ?);";
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, bName);
//            preparedStatement.setString(2, bTitle);
//            preparedStatement.setString(3, bContent);
//            preparedStatement.setInt(4, Integer.parseInt(bGroup));
//            preparedStatement.setInt(5, Integer.parseInt(bStep) + 1);
//            preparedStatement.setInt(6, Integer.parseInt(bIndent) + 1);
//            int result = preparedStatement.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        } finally {
//            try {
//                if (preparedStatement != null)
//                    preparedStatement.close();
//                if (connection != null)
//                    connection.close();
//            } catch (SQLException e2) {
//                e2.printStackTrace();
//            }
//
//        }
    }

    private void replyShape(final String bGroup, final String bStep) {
      String query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?;";
      template.update(query, new PreparedStatementSetter() {
        
        @Override
        public void setValues(PreparedStatement preparedStatement) throws SQLException {
          preparedStatement.setInt(1, Integer.parseInt(bGroup));
          preparedStatement.setInt(2, Integer.parseInt(bStep));

        }
    });

        
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            connection = dataSource.getConnection();
//            String query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?;";
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, Integer.parseInt(bGroup));
//            preparedStatement.setInt(1, Integer.parseInt(bStep));
//            int result = preparedStatement.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        } finally {
//            try {
//                if (preparedStatement != null)
//                    preparedStatement.close();
//                if (connection != null)
//                    connection.close();
//            } catch (SQLException e2) {
//                e2.printStackTrace();
//            }
//
//        }
    }
}
