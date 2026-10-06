import { useAuth } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";
export default function Header() {
  const { username, logout } = useAuth();
  const nav = useNavigate();
  return (
    <div className="header">
      <h1>Trello Clone</h1>
      <div style={{ display: "flex", gap: 12, alignItems: "center" }}>
        <span>{username}</span>
        <button onClick={() => { logout(); nav("/login"); }}>Logout</button>
      </div>
    </div>
  );
}
