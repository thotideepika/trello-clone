import { useEffect, useRef } from "react";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";
export function useWebSocket(boardId: number | null, onMessage: (msg: any) => void) {
  const clientRef = useRef<Client | null>(null);
  useEffect(() => {
    if (!boardId) return;
    const client = new Client({
      webSocketFactory: () => new SockJS("/ws"),
      reconnectDelay: 5000,
      onConnect: () => { client.subscribe(`/topic/board/${boardId}`, (msg) => onMessage(JSON.parse(msg.body))); },
    });
    client.activate();
    clientRef.current = client;
    return () => { client.deactivate(); };
  }, [boardId]);
}
