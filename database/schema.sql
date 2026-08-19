-- UG Campus Smart Service Optimizer Database Schema
--
-- NOTE: This is a placeholder created by Role 3 (Data Loader) so the loader
-- could be built and tested end-to-end before Role 2 (Robert, Database
-- Architect) pushed feature/robert/database-schema. It matches the schema
-- specified for him in Guide_Implementation.md exactly. Robert should review
-- this file and replace/own it going forward -- reconcile on merge if his
-- branch has since diverged.

CREATE TABLE IF NOT EXISTS locations (
    locationId INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    area TEXT NOT NULL,
    type TEXT NOT NULL,
    latitude REAL,
    longitude REAL
);

CREATE TABLE IF NOT EXISTS roads (
    roadId INTEGER PRIMARY KEY AUTOINCREMENT,
    fromLocationId INTEGER NOT NULL,
    toLocationId INTEGER NOT NULL,
    distance REAL NOT NULL,
    travelTime REAL NOT NULL,
    roadConditionWeight REAL DEFAULT 1.0,
    FOREIGN KEY (fromLocationId) REFERENCES locations(locationId),
    FOREIGN KEY (toLocationId) REFERENCES locations(locationId)
);

CREATE TABLE IF NOT EXISTS service_requests (
    requestId INTEGER PRIMARY KEY,
    source INTEGER NOT NULL,
    destination INTEGER NOT NULL,
    category TEXT NOT NULL,
    urgency TEXT NOT NULL,
    timeSubmitted TEXT NOT NULL,
    deadline TEXT NOT NULL,
    status TEXT DEFAULT 'PENDING',
    FOREIGN KEY (source) REFERENCES locations(locationId),
    FOREIGN KEY (destination) REFERENCES locations(locationId)
);

CREATE TABLE IF NOT EXISTS resources (
    resourceId INTEGER PRIMARY KEY,
    type TEXT NOT NULL,
    homeLocation INTEGER NOT NULL,
    capacity INTEGER NOT NULL,
    availabilityStatus TEXT DEFAULT 'AVAILABLE',
    FOREIGN KEY (homeLocation) REFERENCES locations(locationId)
);

CREATE TABLE IF NOT EXISTS algorithm_runs (
    runId INTEGER PRIMARY KEY AUTOINCREMENT,
    algorithmName TEXT NOT NULL,
    inputSize INTEGER NOT NULL,
    timeNs INTEGER NOT NULL,
    memoryKb INTEGER,
    dateRun TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS audit_events (
    eventId INTEGER PRIMARY KEY AUTOINCREMENT,
    eventType TEXT NOT NULL,
    description TEXT NOT NULL,
    timestamp TEXT NOT NULL,
    userId TEXT,
    undoable INTEGER DEFAULT 0
);
