import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";
import Header from "../components/Header";
export default function Dashboard() {
  const [boards, setBoards] = useState<any[]>([]);
  const [name, setName] = useState("");
  const nav = useNavigate();
  useEffect(() => { load(); }, []);
  const load = async () => { const { data } = await api.get("/boards"); setBoards(data); };
  const create = async () => { if (!name.trim()) return; await api.post("/boards", { name }); setName(""); load(); };
  return (
    <>
      <Header />
      <div className="dashboard">
        <h2>Your Boards</h2>
        <div style={{ display: "flex", gap: 12, marginBottom: 24 }}>
          <input placeholder="New board name" value={name} onChange={(e) => setName(e.target.value)} style={{ maxWidth: 300 }} />
          <button onClick={create} style={{ background: "#0052cc", color: "white" }}>Create</button>
        </div>
        <div className="boards-grid">
          {boards.map((b) => <div key={b.id} className="board-card" onClick={() => nav(`/board/${b.id}`)}><h3>{b.name}</h3></div>)}
        </div>
      </div>
    </>
  );
}
