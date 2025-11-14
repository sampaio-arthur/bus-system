import { Toaster } from "@/components/ui/toaster";
import { Toaster as Sonner } from "@/components/ui/sonner";
import { TooltipProvider } from "@/components/ui/tooltip";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Index from "./pages/Index";
import Linhas from "./pages/Linhas";
import Paradas from "./pages/Paradas";
import Turistico from "./pages/Turistico";
import Frota from "./pages/Frota";
import Pessoas from "./pages/Pessoas";
import Cadastro from "./pages/Cadastro";
import NotFound from "./pages/NotFound";

const queryClient = new QueryClient();

const App = () => (
  <QueryClientProvider client={queryClient}>
    <TooltipProvider>
      <Toaster />
      <Sonner />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Index />} />
          <Route path="/linhas" element={<Linhas />} />
          <Route path="/paradas" element={<Paradas />} />
          <Route path="/turistico" element={<Turistico />} />
          <Route path="/frota" element={<Frota />} />
          <Route path="/pessoas" element={<Pessoas />} />
          <Route path="/cadastro" element={<Cadastro />} />
          {/* ADD ALL CUSTOM ROUTES ABOVE THE CATCH-ALL "*" ROUTE */}
          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>
    </TooltipProvider>
  </QueryClientProvider>
);

export default App;
