# Dataset Construction Evidence Note

The dataset was constructed to represent realistic operational activities at the University of Ghana, Legon campus. The locations include lecture halls, hostels, departments, facilities, gates, dining areas, service points, and other important campus locations. The road distances and travel times are approximate but designed to be realistic for walking and shuttle movement around campus. The service requests and resources represent common campus operations such as IT support, maintenance, room booking, security, cleaning, shuttle services, and equipment loans.

## Algorithm Parameters Derived from Student Index Numbers

The following parameters were derived from the last digits of team members' student IDs as required by the project specification.

### 1. Hash Table Initial Size

Using the last two digits of three team members:

- Matin Seity: 16
- Robert Ganyoh Goodness: 88
- Prosper Sokari: 84

16 + 88 + 84 = 188

The next prime number after 188 is **191**.

Therefore:

**Hash table initial size = 191**

### 2. Priority Weight for CRITICAL Requests

Using the last digit of the Team Lead's student ID:

- Kwakye Ishmael Affum: 22032451
- Last digit = 1

1 × 2 = **2.0**

Therefore:

**CRITICAL request priority weight = 2.0**

### 3. Random Seed for Data Generation

Using the last two digits of two team members:

- Matin Seity: 16
- Robert Ganyoh Goodness: 88

Concatenating them gives:

**Random seed = 1688**

### 4. Road Penalty Multiplier

Using the last digits of four team members:

- Matin Seity: 6
- Robert Ganyoh Goodness: 8
- Prosper Sokari: 4
- Kennedy Sarfo: 6

Average:

(6 + 8 + 4 + 6) / 4 = 6

Therefore:

**Road penalty multiplier = 1.6**

These derived parameters will be used by the appropriate data structures and algorithms during later implementation phases.