import { Draggable } from "react-beautiful-dnd";
import { useState } from "react";
interface Card { id: number; title: string; description: string; position: number; }
export default function CardItem({ card, index }: { card: Card; index: number }) {
  const [editing, setEditing] = useState(false);
  const [title, setTitle] = useState(card.title);
  return (
    <Draggable draggableId={`card-${card.id}`} index={index}>
      {(provided) => (
        <div className="card-item" ref={provided.innerRef} {...provided.draggableProps} {...provided.dragHandleProps} onClick={() => setEditing(true)}>
          {editing ? <input value={title} onChange={(e) => setTitle(e.target.value)} onBlur={() => setEditing(false)} autoFocus /> : <p>{card.title}</p>}
        </div>
      )}
    </Draggable>
  );
}
