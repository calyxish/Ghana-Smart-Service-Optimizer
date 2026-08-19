package com.stackoverflow.smartservice.data.database;

import com.stackoverflow.smartservice.data.database.model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD operations for the locations table.
 *
 * PLACEHOLDER (see DatabaseConnection.java note): owned by Role 2 (Robert)
 * per Guide_Implementation.md; written here to unblock/test the Role 3
 * loader ahead of his branch.
 */
public class LocationDAO {

    public void insert(Location location) throws SQLException {
        String sql = "INSERT INTO locations (locationId, name, area, type, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, location.getLocationId());
            ps.setString(2, location.getName());
            ps.setString(3, location.getArea());
            ps.setString(4, location.getType());
            ps.setDouble(5, location.getLatitude());
            ps.setDouble(6, location.getLongitude());
            ps.executeUpdate();
        }
    }

    public Location getById(int id) throws SQLException {
        String sql = "SELECT * FROM locations WHERE locationId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    public List<Location> getAll() throws SQLException {
        List<Location> results = new ArrayList<>();
        String sql = "SELECT * FROM locations ORDER BY locationId";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                results.add(mapRow(rs));
            }
        }
        return results;
    }

    public void update(Location location) throws SQLException {
        String sql = "UPDATE locations SET name = ?, area = ?, type = ?, latitude = ?, longitude = ? WHERE locationId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, location.getName());
            ps.setString(2, location.getArea());
            ps.setString(3, location.getType());
            ps.setDouble(4, location.getLatitude());
            ps.setDouble(5, location.getLongitude());
            ps.setInt(6, location.getLocationId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM locations WHERE locationId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Location mapRow(ResultSet rs) throws SQLException {
        return new Location(
                rs.getInt("locationId"),
                rs.getString("name"),
                rs.getString("area"),
                rs.getString("type"),
                rs.getDouble("latitude"),
                rs.getDouble("longitude")
        );
    }
}
