package com.stackoverflow.smartservice.data.database;

import com.stackoverflow.smartservice.data.database.model.ServiceRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD operations for the service_requests table.
 *
 * PLACEHOLDER (see DatabaseConnection.java note): owned by Role 2 (Robert)
 * per Guide_Implementation.md; written here to unblock/test the Role 3
 * loader ahead of his branch.
 */
public class ServiceRequestDAO {

    public void insert(ServiceRequest request) throws SQLException {
        String sql = "INSERT INTO service_requests (requestId, source, destination, category, urgency, timeSubmitted, deadline, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, request.getRequestId());
            ps.setInt(2, request.getSource());
            ps.setInt(3, request.getDestination());
            ps.setString(4, request.getCategory());
            ps.setString(5, request.getUrgency());
            ps.setString(6, request.getTimeSubmitted());
            ps.setString(7, request.getDeadline());
            ps.setString(8, request.getStatus());
            ps.executeUpdate();
        }
    }

    public ServiceRequest getById(int id) throws SQLException {
        String sql = "SELECT * FROM service_requests WHERE requestId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    public List<ServiceRequest> getAll() throws SQLException {
        List<ServiceRequest> results = new ArrayList<>();
        String sql = "SELECT * FROM service_requests ORDER BY requestId";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                results.add(mapRow(rs));
            }
        }
        return results;
    }

    public void update(ServiceRequest request) throws SQLException {
        String sql = "UPDATE service_requests SET source = ?, destination = ?, category = ?, urgency = ?, timeSubmitted = ?, deadline = ?, status = ? WHERE requestId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, request.getSource());
            ps.setInt(2, request.getDestination());
            ps.setString(3, request.getCategory());
            ps.setString(4, request.getUrgency());
            ps.setString(5, request.getTimeSubmitted());
            ps.setString(6, request.getDeadline());
            ps.setString(7, request.getStatus());
            ps.setInt(8, request.getRequestId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM service_requests WHERE requestId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private ServiceRequest mapRow(ResultSet rs) throws SQLException {
        return new ServiceRequest(
                rs.getInt("requestId"),
                rs.getInt("source"),
                rs.getInt("destination"),
                rs.getString("category"),
                rs.getString("urgency"),
                rs.getString("timeSubmitted"),
                rs.getString("deadline"),
                rs.getString("status")
        );
    }
}
