#include <iostream>
#include <jni.h>
#include <SDL.h>
#include <stdio.h>

#include "window.h"

namespace yrai {

    const int STEPS = 10;

    const int WIN_WIDTH = 640;
    const int WIN_HEIGHT = 480;
    const int FRA_WIDTH = WIN_WIDTH / STEPS;
    const int FRA_HEIGHT = WIN_HEIGHT / STEPS;

    void renderFrame(SDL_Renderer * ren, int frame) {
        int i = frame % STEPS;
        SDL_SetRenderDrawColor(ren, 0, 0, 0, SDL_ALPHA_OPAQUE);
        SDL_RenderClear(ren);

        SDL_SetRenderDrawColor(ren, 255, 255, 255, SDL_ALPHA_OPAQUE);
        SDL_RenderDrawLine(ren, 0,0,FRA_WIDTH * i, FRA_HEIGHT * i);
        SDL_RenderPresent(ren);
        std::cout << i << std::endl;
    }

    void window_init() {
        std::cout << "Hello World!" << std::endl;
            int init = SDL_Init(SDL_INIT_VIDEO);
            if (init != 0) {
                std::cout << "SDL_Init Error: " << SDL_GetError() << std::endl;
                return;
            }
            SDL_Window *win = SDL_CreateWindow("Hello World!", 100, 100, WIN_WIDTH, WIN_HEIGHT, SDL_WINDOW_SHOWN);
            if (win == nullptr){
                std::cout << "SDL_CreateWindow Error: " << SDL_GetError() << std::endl;
                SDL_Quit();
                return;
            }

//            SDL_Renderer *ren = SDL_CreateRenderer(win, -1, SDL_RENDERER_ACCELERATED | SDL_RENDERER_PRESENTVSYNC);
            SDL_Renderer *ren = SDL_CreateRenderer(win, -1, 0);
            if (ren == nullptr){
                SDL_DestroyWindow(win);
                std::cout << "SDL_CreateRenderer Error: " << SDL_GetError() << std::endl;
                SDL_Quit();
                return;
            }

            int i = 0;
            bool is_running = true;
            SDL_Event event;
            while (is_running) {
                while (SDL_PollEvent(&event)) {
                    if (event.type == SDL_QUIT) {
                        is_running = false;
                    }
                }
                renderFrame(ren, i);
                SDL_Delay(16);
                i++;
            }

            std::cout << "Done!" << std::endl;
            SDL_DestroyRenderer(ren);
            SDL_DestroyWindow(win);
            SDL_Quit();
            return;
    }
}