import { useEffect, useState, useCallback } from "react";
import { useParams } from "react-router-dom";
import { DragDropContext, DropResult } from "react-beautiful-dnd";
import api from "../api/axios";
import Header from "../components/Header";
import Column from "../components/Column";
import { useWebSocket } from "../hooks/useWebSocket";
export default function BoardView() {
  const { id } = useParams();
  const boardId = Number(id);
  const [board, setBoard] = useState<any>(null);
  const [newList, setNewList] = useState("");
  const load = useCallback(async () => { const { data } = await api.get(`/boards/${boardId}`); setBoard(data); }, [boardId]);
  useEffect(() => { load(); }, [load]);
  useWebSocket(boardId, () => load());
  const addList = async () => { if (!newList.trim()) return; await api.post("/lists", { boardId, name: newList }); setNewList(""); load(); };
  const onDragEnd = async (result: DropResult) => {
    if (!result.destination) return;
    const cardId = Number(result.draggableId.replace("card-", ""));
    const targetListId = Number(result.destination.droppableId.replace("list-", ""));
    await api.post("/cards/move", { cardId, targetListId, newPosition: result.destination.index });
    load();
  };
  if (!board) return <div>Loading...</div>;
  return (
    <>
      <Header />
      <div className="board-view">
        <h2>{board.name}</h2>
        <DragDropContext onDragEnd={onDragEnd}>
          <div className="lists-container">
            {board.lists?.map((list: any) => <Column key={list.id} list={list} boardId={boardId} onRefresh={load} />)}
            <div className="add-list">
              <input placeholder="New list name" value={newList} onChange={(e) => setNewList(e.target.value)} onKeyDown={(e) => e.key === "Enter" && addList()} />
              <button onClick={addList} style={{ marginTop: 8 }}>+ Add list</button>
            </div>
          </div>
        </DragDropContext>
      </div>
    </>
  );
}
