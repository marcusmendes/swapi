import { Request, Response } from 'express';

/**
 * Constroller responsável pela rota padrão do API '/'
 */
class DefaultController {
  /**
   * Método chamado quando a rota '/' é requisitada
   *
   * @param req Request
   * @param res Response
   */
  public index(req:Request, res:Response): Response {
    return res.json({ message: 'Star Wars API' });
  }
}

export default new DefaultController();
