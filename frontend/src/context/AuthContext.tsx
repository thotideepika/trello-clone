import { createContext, useContext, useState, ReactNode } from "react";
interface AuthCtx { token: string | null; username: string | null; login: (t: string, u: string) => void; logout: () => void; }
const Ctx = createContext<AuthCtx>(null!);
export function AuthProvider({ children }: { children: ReactNode }) {
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [username, setUsername] = useState(localStorage.getItem("username"));
  const login = (t: string, u: string) => { localStorage.setItem("token", t); localStorage.setItem("username", u); setToken(t); setUsername(u); };
  const logout = () => { localStorage.clear(); setToken(null); setUsername(null); };
  return <Ctx.Provider value={{ token, username, login, logout }}>{children}</Ctx.Provider>;
}
export const useAuth = () => useContext(Ctx);
