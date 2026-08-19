package com.stackoverflow.smartservice.data.database;

import com.stackoverflow.smartservice.data.database.model.Road;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD operations for the roads table.
 *
 * PLACEHOLDER (see DatabaseConnection.java note): owned by Role 2 (Robert)
 * per Guide_Implementation.md; written here to unblock/test the Role 3
 * loader ahead of his branch.
 */
public class RoadDAO {

    public void insert(Road road) throws SQLException {
        String sql = "INSERT INTO roads (fromLocationId, toLocationId, distance, travelTime, roadConditionWeight) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, road.getFromLocationId());
            ps.setInt(2, road.getToLocationId());
            ps.setDouble(3, road.getDistance());
            ps.setDouble(4, road.getTravelTime());
            ps.setDouble(5, road.getRoadConditionWeight());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    road.setRoadId(keys.getInt(1));
                }
            }
        }
    }

    public Road getById(int roadId) throws SQLException {
        String sql = "SELECT * FROM roads WHERE roadId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roadId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    public List<Road> getAll() throws SQLException {
        List<Road> results = new ArrayList<>();
        String sql = "SELECT * FROM roads ORDER BY roadId";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                results.add(mapRow(rs));
            }
        }
        return results;
    }

    public void update(Road road) throws SQLException {
        String sql = "UPDATE roads SET fromLocationId = ?, toLocationId = ?, distance = ?, travelTime = ?, roadConditionWeight = ? WHERE roadId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, road.getFromLocationId());
            ps.setInt(2, road.getToLocationId());
            ps.setDouble(3, road.getDistance());
            ps.setDouble(4, road.getTravelTime());
            ps.setDouble(5, road.getRoadConditionWeight());
            ps.setInt(6, road.getRoadId());
            ps.executeUpdate();
        }
    }

    public void delete(int roadId) throws SQLException {
        String sql = "DELETE FROM roads WHERE roadId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roadId);
            ps.executeUpdate();
        }
    }

    private Road mapRow(ResultSet rs) throws SQLException {
        Road road = new Road(
                rs.getInt("fromLocationId"),
                rs.getInt("toLocationId"),
                rs.getDouble("distance"),
                rs.getDouble("travelTime"),
                rs.getDouble("roadConditionWeight")
        );
        road.setRoadId(rs.getInt("roadId"));
        return road;
    }
}
