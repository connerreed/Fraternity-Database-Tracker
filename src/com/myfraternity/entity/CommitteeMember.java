package com.myfraternity.entity;

import com.myfraternity.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CommitteeMember {
    private int memberId;
    private int committeeId;

    public CommitteeMember(int memberId, int committeeId) {
        this.memberId = memberId;
        this.committeeId = committeeId;
    }

    public CommitteeMember() {

    }

    public Vector<Object> getVector() {
        Vector<Object> vector = new Vector<>();
        vector.add(memberId);
        vector.add(committeeId);
        return vector;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(int committeeId) {
        this.committeeId = committeeId;
    }

    @Override
    public String toString() {
        return "CommitteeMember{" +
                "member_id=" + memberId +
                ", committee_id=" + committeeId +
                '}';
    }




    public List<Committee> getCommitteesForMember(Member member) {
        List<Committee> committees = new ArrayList<>();
        String sql = "SELECT c.* FROM committee c "
                + "JOIN committee_member cm ON c.committee_id = cm.committee_id "
                + "WHERE cm.member_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, member.getMemberId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Committee committee = new Committee();
                committee.setCommitteeId(rs.getInt("committee_id"));
                committee.setName(rs.getString("name"));
                committee.setDescription(rs.getString("description"));
                committee.setChapterId(rs.getInt("chapter_id"));
                committees.add(committee);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return committees;
    }
}
