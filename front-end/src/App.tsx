import { Toaster } from "@/components/ui/toaster";
import { Toaster as Sonner } from "@/components/ui/sonner";
import { TooltipProvider } from "@/components/ui/tooltip";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Layout } from "@/components/Layout";
import Dashboard from "./pages/Dashboard";
import Cidades from "./pages/Cidades";
import PontosParada from "./pages/PontosParada";
import Linhas from "./pages/Linhas";
import Veiculos from "./pages/Veiculos";
import Viagens from "./pages/Viagens";
import Atendimento from "./pages/Atendimento";
import NotFound from "./pages/NotFound";

const queryClient = new QueryClient();

const App = () => (
  <QueryClientProvider client={queryClient}>
    <TooltipProvider>
      <Toaster />
      <Sonner />
      <BrowserRouter>
        <Layout>
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/cidades" element={<Cidades />} />
            <Route path="/pontos-parada" element={<PontosParada />} />
            <Route path="/linhas" element={<Linhas />} />
            <Route path="/veiculos" element={<Veiculos />} />
            <Route path="/viagens" element={<Viagens />} />
            <Route path="/atendimento" element={<Atendimento />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </Layout>
      </BrowserRouter>
    </TooltipProvider>
  </QueryClientProvider>
);

export default App;
