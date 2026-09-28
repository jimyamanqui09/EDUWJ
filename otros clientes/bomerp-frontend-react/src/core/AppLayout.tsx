import { Link, NavLink, Outlet } from 'react-router-dom'
import './AppLayout.css'

export default function AppLayout() {
  return (
    <>
      <header className="header">
        <h1>
          <Link to="/" className="home-link">
            <img src="/favicon.svg" alt="" height={30} />
          </Link>
          BomERP
        </h1>
      </header>

      <div className="body">
        <aside className="sidebar">
          <nav>
            <NavLink to="/catalogo/categorias" className={({ isActive }) => (isActive ? 'active' : '')}>
              Categorías
            </NavLink>
          </nav>
        </aside>

        <main className="content">
          <Outlet />
        </main>
      </div>
    </>
  )
}
