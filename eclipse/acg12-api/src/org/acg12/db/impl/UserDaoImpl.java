package org.acg12.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.acg12.bean.User;
import org.acg12.db.UserDao;
import org.acg12.utils.JdbcUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public int insert(User user) {
		int num = -1;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into user(username,password,nick,sex,avatar,createdAt,updatedAt) values(?,?,?,?,?,?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());
			st.setString(3, user.getNick());
			st.setBoolean(4, user.isSex());
			st.setString(5, user.getAvatar());
			st.setString(6, user.getCreatedAt());
			st.setString(7, user.getUpdatedAt());
			num = st.executeUpdate();
			if (num > 0) {
				System.out.println("插入成功！！");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			 JdbcUtils.release(conn, st, rs);
		}
		return num;
	}
	
	@Override
	public int update(User user) {
		int num = -1;
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "update user set nick=?,sex=?,avatar=?,updatedAt=?, where id=?";
            st = conn.prepareStatement(sql);
            st.setString(1, user.getNick());
            st.setBoolean(2, user.isSex());
            st.setString(3, user.getAvatar());
            st.setString(4, user.getUpdatedAt());
            st.setInt(5, user.getUserId());
            num = st.executeUpdate();
            if(num > 0){
                System.out.println("更新成功！！");
            }
        }catch (Exception e) {
            e.printStackTrace();
            
        }finally{
            JdbcUtils.release(conn, st, rs);
        }
        return num;
	}

	@Override
	public int delete(User user) {
		int num = -1;
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "delete from user where id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, user.getUserId());
            num = st.executeUpdate();
            if(num > 0){
                System.out.println("删除成功！！");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn, st, rs);
        }
        return num;
	}
	
	@Override
	public User find(String username) {
		User user = null;
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "select * from user where username=?";
            st = conn.prepareStatement(sql);
            st.setString(1, username);
            rs = st.executeQuery();
            if(rs.next()){
            	user = new User();
            	user.setUserId(rs.getInt("id"));
            	user.setUsername(rs.getString("username"));
            	user.setAvatar(rs.getString("avatar"));
            	user.setSex(rs.getBoolean("sex"));
            	user.setUpdatedAt(rs.getString("updatedAt"));
            	user.setCreatedAt(rs.getString("createdAt"));
            }
        }catch (Exception e) {
        	e.printStackTrace();
        }finally{
            JdbcUtils.release(conn, st, rs);
        }
		return user;
	}

	@Override
	public User find(int id) {
		User user = null;
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "select * from user where id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
            	user = new User();
            	user.setUserId(rs.getInt("id"));
            	user.setUsername(rs.getString("username"));
            	user.setAvatar(rs.getString("avatar"));
            	user.setSex(rs.getBoolean("sex"));
            	user.setUpdatedAt(rs.getString("updatedAt"));
            	user.setCreatedAt(rs.getString("createdAt"));
            }
        }catch (Exception e) {
        	e.printStackTrace();
        }finally{
            JdbcUtils.release(conn, st, rs);
        }
		return user;
	}

}
