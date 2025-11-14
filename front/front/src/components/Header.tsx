import { Bus, Menu } from "lucide-react";
import { Link } from "react-router-dom";
import { Button } from "./ui/button";
import { useState } from "react";

const Header = () => {
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

  return (
    <header className="sticky top-0 z-50 w-full border-b bg-card/95 backdrop-blur supports-[backdrop-filter]:bg-card/80 shadow-card">
      <div className="container flex h-16 items-center justify-between">
        <Link to="/" className="flex items-center gap-2 font-bold text-xl">
          <div className="p-2 rounded-lg bg-gradient-transit">
            <Bus className="h-6 w-6 text-primary-foreground" />
          </div>
          <span className="bg-gradient-transit bg-clip-text text-transparent">
            Araranguá Bus
          </span>
        </Link>

        <nav className="hidden md:flex items-center gap-6">
          <Link to="/" className="text-sm font-medium transition-colors hover:text-primary">
            Início
          </Link>
          <Link to="/linhas" className="text-sm font-medium transition-colors hover:text-primary">
            Linhas
          </Link>
          <Link to="/paradas" className="text-sm font-medium transition-colors hover:text-primary">
            Paradas
          </Link>
          <Link to="/turistico" className="text-sm font-medium transition-colors hover:text-primary">
            Pontos Turísticos
          </Link>
          <Link to="/frota" className="text-sm font-medium transition-colors hover:text-primary">
            Frota
          </Link>
          <Link to="/pessoas" className="text-sm font-medium transition-colors hover:text-primary">
            Pessoas
          </Link>
          <Link to="/cadastro" className="text-sm font-medium transition-colors hover:text-primary">
            Cadastro
          </Link>
        </nav>

        <Button
          variant="ghost"
          size="icon"
          className="md:hidden"
          onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
        >
          <Menu className="h-6 w-6" />
        </Button>
      </div>

      {mobileMenuOpen && (
        <nav className="md:hidden border-t bg-card p-4 flex flex-col gap-4 animate-slide-in">
          <Link
            to="/"
            className="text-sm font-medium transition-colors hover:text-primary"
            onClick={() => setMobileMenuOpen(false)}
          >
            Início
          </Link>
          <Link
            to="/linhas"
            className="text-sm font-medium transition-colors hover:text-primary"
            onClick={() => setMobileMenuOpen(false)}
          >
            Linhas
          </Link>
          <Link
            to="/paradas"
            className="text-sm font-medium transition-colors hover:text-primary"
            onClick={() => setMobileMenuOpen(false)}
          >
            Paradas
          </Link>
          <Link
            to="/turistico"
            className="text-sm font-medium transition-colors hover:text-primary"
            onClick={() => setMobileMenuOpen(false)}
          >
            Pontos Turísticos
          </Link>
          <Link
            to="/frota"
            className="text-sm font-medium transition-colors hover:text-primary"
            onClick={() => setMobileMenuOpen(false)}
          >
            Frota
          </Link>
          <Link
            to="/pessoas"
            className="text-sm font-medium transition-colors hover:text-primary"
            onClick={() => setMobileMenuOpen(false)}
          >
            Pessoas
          </Link>
          <Link
            to="/cadastro"
            className="text-sm font-medium transition-colors hover:text-primary"
            onClick={() => setMobileMenuOpen(false)}
          >
            Cadastro
          </Link>
        </nav>
      )}
    </header>
  );
};

export default Header;
