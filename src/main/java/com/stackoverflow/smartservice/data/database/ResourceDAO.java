package com.stackoverflow.smartservice.data.database;

import com.stackoverflow.smartservice.data.database.model.Resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD operations for the resources table.
 *
 * PLACEHOLDER (see DatabaseConnection.java note): owned by Role 2 (Robert)
 * per Guide_Implementation.md; written here to unblock/test the Role 3
 * loader ahead of his branch.
 */
public class ResourceDAO {

    public void insert(Resource resource) throws SQLException {
        String sql = "INSERT INTO resources (resourceId, type, homeLocation, capacity, availabilityStatus) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, resource.getResourceId());
            ps.setString(2, resource.getType());
            ps.setInt(3, resource.getHomeLocation());
            ps.setInt(4, resource.getCapacity());
            ps.setString(5, resource.getAvailabilityStatus());
            ps.executeUpdate();
        }
    }

    public Resource getById(int id) throws SQLException {
        String sql = "SELECT * FROM resources WHERE resourceId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    public List<Resource> getAll() throws SQLException {
        List<Resource> results = new ArrayList<>();
        String sql = "SELECT * FROM resources ORDER BY resourceId";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                results.add(mapRow(rs));
            }
        }
        return results;
    }

    public void update(Resource resource) throws SQLException {
        String sql = "UPDATE resources SET type = ?, homeLocation = ?, capacity = ?, availabilityStatus = ? WHERE resourceId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, resource.getType());
            ps.setInt(2, resource.getHomeLocation());
            ps.setInt(3, resource.getCapacity());
            ps.setString(4, resource.getAvailabilityStatus());
            ps.setInt(5, resource.getResourceId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM resources WHERE resourceId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Resource mapRow(ResultSet rs) throws SQLException {
        return new Resource(
                rs.getInt("resourceId"),
                rs.getString("type"),
                rs.getInt("homeLocation"),
                rs.getInt("capacity"),
                rs.getString("availabilityStatus")
        );
    }
}
