import { Droppable } from "react-beautiful-dnd";
import { useState } from "react";
import CardItem from "./CardItem";
import api from "../api/axios";
interface Card { id: number; title: string; description: string; position: number; }
interface List { id: number; name: string; position: number; cards: Card[]; }
export default function Column({ list, boardId, onRefresh }: { list: List; boardId: number; onRefresh: () => void }) {
  const [newCard, setNewCard] = useState("");
  const [adding, setAdding] = useState(false);
  const addCard = async () => {
    if (!newCard.trim()) return;
    await api.post("/cards", { listId: list.id, title: newCard, boardId });
    setNewCard(""); setAdding(false); onRefresh();
  };
  return (
    <div className="list">
      <div className="list-header"><h3>{list.name}</h3></div>
      <Droppable droppableId={`list-${list.id}`}>
        {(provided) => (
          <div ref={provided.innerRef} {...provided.droppableProps} style={{ minHeight: 20 }}>
            {list.cards.map((card, i) => <CardItem key={card.id} card={card} index={i} />)}
            {provided.placeholder}
          </div>
        )}
      </Droppable>
      {adding ? (
        <div style={{ marginTop: 8 }}>
          <input placeholder="Card title" value={newCard} onChange={(e) => setNewCard(e.target.value)} onKeyDown={(e) => e.key === "Enter" && addCard()} autoFocus />
          <button onClick={addCard} style={{ background: "#0052cc", color: "white", marginTop: 4 }}>Add</button>
        </div>
      ) : (<button className="add-card-btn" onClick={() => setAdding(true)}>+ Add a card</button>)}
    </div>
  );
}
