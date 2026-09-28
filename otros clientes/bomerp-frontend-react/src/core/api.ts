const baseUrl = import.meta.env.VITE_API_BASE_URL as string

export function buildUrl(path: string): string {
  const normalizedPath = path.startsWith('/') ? path : `/${path}`
  return `${baseUrl}${normalizedPath}`
}

export async function apiFetch(path: string, init: RequestInit = {}): Promise<Response> {
  const traceId = crypto.randomUUID()
  const response = await fetch(buildUrl(path), {
    ...init,
    headers: {
      ...init.headers,
      'X-Trace-ID': traceId,
    },
  })

  if (!response.ok) {
    throw new Error(`Error ${response.status} en ${path}`)
  }

  return response
}
