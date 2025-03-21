import React, { useState, useEffect } from "react";
import axios from "axios";
import "./App.css";

const App = () => {
  const [policies, setPolicies] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [minPremium, setMinPremium] = useState("");
  const [maxPremium, setMaxPremium] = useState("");
  const [policyType, setPolicyType] = useState("");
  const [minCoverage, setMinCoverage] = useState("");
  const [sortOrder, setSortOrder] = useState("");

  useEffect(() => {
    fetchPolicies();
  }, []);

  const fetchPolicies = async () => {
    try {
      const response = await axios.get("http://localhost:8080/policies");
      setPolicies(response.data);
    } catch (error) {
      console.error("Error fetching policies:", error);
    }
  };

  const handleSearch = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/policies/search?name=${searchTerm}`
      );
      setPolicies(response.data);
    } catch (error) {
      console.error("Error searching policies:", error);
    }
  };

  const handleFilter = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/policies/filter?minPremium=${minPremium}&maxPremium=${maxPremium}&type=${policyType}&coverage=${minCoverage}`
      );
      setPolicies(response.data);
    } catch (error) {
      console.error("Error filtering policies:", error);
    }
  };

  const handleSort = async (order) => {
    setSortOrder(order);
    const sortedPolicies = [...policies].sort((a, b) =>
      order === "asc" ? a.premium - b.premium : b.premium - a.premium
    );
    setPolicies(sortedPolicies);
  };

  return (
    <div className="container">
      <h1>Insurance Policy Finder</h1>

      {/* Search Bar */}
      <div className="search-container">
        <input
          type="text"
          placeholder="Search by policy name..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <button onClick={handleSearch}>Search</button>
      </div>

      {/* Filters */}
      <div className="filters">
        <input
          type="number"
          placeholder="Min Premium"
          value={minPremium}
          onChange={(e) => setMinPremium(e.target.value)}
        />
        <input
          type="number"
          placeholder="Max Premium"
          value={maxPremium}
          onChange={(e) => setMaxPremium(e.target.value)}
        />
        <select
          value={policyType}
          onChange={(e) => setPolicyType(e.target.value)}
        >
          <option value="">All Types</option>
          <option value="Term Life">Term Life</option>
          <option value="Health">Health</option>
          <option value="Vehicle">Vehicle</option>
        </select>
        <input
          type="number"
          placeholder="Min Coverage"
          value={minCoverage}
          onChange={(e) => setMinCoverage(e.target.value)}
        />
        <button onClick={handleFilter}>Apply Filters</button>
      </div>

      {/* Sorting */}
      <div className="sorting">
        <button onClick={() => handleSort("asc")}>Sort by Premium ↑</button>
        <button onClick={() => handleSort("desc")}>Sort by Premium ↓</button>
      </div>

      {/* Policy Table */}
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Type</th>
            <th>Premium</th>
            <th>Coverage</th>
          </tr>
        </thead>
        <tbody>
          {policies.length > 0 ? (
            policies.map((policy) => (
              <tr key={policy.id}>
                <td>{policy.name}</td>
                <td>{policy.type}</td>
                <td>₹{policy.premium}</td>
                <td>₹{policy.coverage}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4">No policies found</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default App;
