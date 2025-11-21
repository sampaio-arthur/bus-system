import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Button } from "@/components/ui/button";
import { Edit, Trash2 } from "lucide-react";
import { Badge } from "@/components/ui/badge";

interface Column<T> {
  key: keyof T | string;
  label: string;
  render?: (value: any, item: T) => React.ReactNode;
}

interface DataTableProps<T> {
  data: T[];
  columns: Column<T>[];
  onEdit?: (item: T) => void;
  onDelete?: (item: T) => void;
  idKey?: keyof T;
}

export function DataTable<T extends Record<string, any>>({
  data,
  columns,
  onEdit,
  onDelete,
  idKey = 'id' as keyof T,
}: DataTableProps<T>) {
  const getValue = (item: T, key: string): any => {
    const keys = key.split('.');
    let value: any = item;
    for (const k of keys) {
      value = value?.[k];
    }
    return value;
  };

  return (
    <div className="border rounded-lg overflow-hidden bg-card">
      <Table>
        <TableHeader>
          <TableRow className="bg-muted/50">
            {columns.map((col, idx) => (
              <TableHead key={idx} className="font-semibold">
                {col.label}
              </TableHead>
            ))}
            {(onEdit || onDelete) && (
              <TableHead className="text-right font-semibold">Ações</TableHead>
            )}
          </TableRow>
        </TableHeader>
        <TableBody>
          {data.length === 0 ? (
            <TableRow>
              <TableCell colSpan={columns.length + (onEdit || onDelete ? 1 : 0)} className="text-center text-muted-foreground py-8">
                Nenhum registro encontrado
              </TableCell>
            </TableRow>
          ) : (
            data.map((item, rowIdx) => (
              <TableRow key={item[idKey] || rowIdx} className="hover:bg-muted/30">
                {columns.map((col, colIdx) => {
                  const value = getValue(item, col.key as string);
                  return (
                    <TableCell key={colIdx}>
                      {col.render ? col.render(value, item) : value}
                    </TableCell>
                  );
                })}
                {(onEdit || onDelete) && (
                  <TableCell className="text-right">
                    <div className="flex justify-end gap-2">
                      {onEdit && (
                        <Button
                          variant="outline"
                          size="sm"
                          onClick={() => onEdit(item)}
                          className="h-8 w-8 p-0"
                        >
                          <Edit className="h-4 w-4" />
                        </Button>
                      )}
                      {onDelete && (
                        <Button
                          variant="outline"
                          size="sm"
                          onClick={() => onDelete(item)}
                          className="h-8 w-8 p-0 text-destructive hover:text-destructive"
                        >
                          <Trash2 className="h-4 w-4" />
                        </Button>
                      )}
                    </div>
                  </TableCell>
                )}
              </TableRow>
            ))
          )}
        </TableBody>
      </Table>
    </div>
  );
}

export const ActiveBadge = ({ ativo }: { ativo: boolean }) => (
  <Badge variant={ativo ? "default" : "secondary"} className={ativo ? "bg-success" : ""}>
    {ativo ? "Ativo" : "Inativo"}
  </Badge>
);
