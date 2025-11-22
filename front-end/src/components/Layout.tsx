import { cn } from "@/lib/utils";
import {
  BarChart3,
  Bus,
  Calendar,
  LayoutDashboard,
  Map,
  MapPin,
  Route,
  Settings,
  Ticket,
  Users,
  Wrench,
} from "lucide-react";
import { ReactNode } from "react";
import { Link, useLocation } from "react-router-dom";

interface LayoutProps {
  children: ReactNode;
}

const navItems = [
  { path: "/", icon: LayoutDashboard, label: "Dashboard" },
  { path: "/cidades", icon: MapPin, label: "Cidades" },
  { path: "/pontos-parada", icon: Map, label: "Pontos de Parada" },
  { path: "/pontos-turisticos", icon: Map, label: "Pontos Turisticos" },
  { path: "/linhas", icon: Route, label: "Linhas" },
  { path: "/itinerarios", icon: Route, label: "Itinerarios" },
  { path: "/veiculos", icon: Bus, label: "Veiculos" },
  { path: "/pessoas", icon: Users, label: "Pessoas" },
  { path: "/viagens", icon: Calendar, label: "Viagens" },
  { path: "/passagens", icon: Ticket, label: "Passagens" },
  { path: "/manutencoes", icon: Wrench, label: "Manutencoes" },
  { path: "/relatorios", icon: BarChart3, label: "Relatorios" },
];

export const Layout = ({ children }: LayoutProps) => {
  const location = useLocation();

  return (
    <div className="flex min-h-screen bg-background">
      {/* Sidebar */}
      <aside className="w-64 bg-sidebar border-r border-sidebar-border flex flex-col">
        <div className="p-6 border-b border-sidebar-border">
          <h1 className="text-xl font-bold text-sidebar-foreground flex items-center gap-2">
            <Bus className="h-6 w-6 text-sidebar-primary" />
            Bus-Sys
          </h1>
          <p className="text-sm text-sidebar-foreground/70 mt-1">
            Sistema de Gestao
          </p>
        </div>

        <nav className="flex-1 p-4 space-y-1 overflow-y-auto">
          {navItems.map((item) => {
            const isActive = location.pathname === item.path;
            const Icon = item.icon;
            return (
              <Link
                key={item.path}
                to={item.path}
                className={cn(
                  "flex items-center gap-3 px-4 py-3 rounded-lg transition-all",
                  isActive
                    ? "bg-sidebar-accent text-sidebar-accent-foreground font-medium"
                    : "text-sidebar-foreground/70 hover:bg-sidebar-accent/50 hover:text-sidebar-foreground"
                )}
              >
                <Icon className="h-5 w-5" />
                <span>{item.label}</span>
              </Link>
            );
          })}
        </nav>

        <div className="p-4 border-t border-sidebar-border">
          <Link
            to="/configuracoes"
            className="flex items-center gap-3 px-4 py-3 rounded-lg text-sidebar-foreground/70 hover:bg-sidebar-accent/50 hover:text-sidebar-foreground transition-all"
          >
            <Settings className="h-5 w-5" />
            <span>Configuracoes</span>
          </Link>
        </div>
      </aside>

      {/* Main Content */}
      <main className="flex-1 overflow-y-auto">
        <div className="container mx-auto p-6 max-w-7xl">{children}</div>
      </main>
    </div>
  );
};
